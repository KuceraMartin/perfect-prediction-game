package console

import java.util.concurrent.TimeUnit.{MILLISECONDS, SECONDS}

import scala.annotation.tailrec
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.StdIn.readLine
import scala.sys.exit

import akka.actor.ActorSystem
import akka.stream.SystemMaterializer
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import structures.GameType
import structures.request.Play
import structures.response.Game


case class Config(
  gameType: GameType.Member = GameType.NonNashian,
  rows: Int = 3,
  cols: Int = 3,
)


object Main extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  val config: Config = args.foldLeft(Config()) { (config, arg) =>
    val dim = "(\\d+)x(\\d+)".r
    arg match {
      case "nash" | "--nash" => config.copy(gameType = GameType.Nashian)
      case "non-nash" | "--non-nash" => config.copy(gameType = GameType.NonNashian)
      case dim(r, c) => config.copy(rows = r.toInt, cols = c.toInt)
      case _ =>
        println(s"Invalid argument `$arg`.")
        exit(1)
    }
  }


  implicit val system = ActorSystem()
  implicit val materializer = SystemMaterializer(system).materializer


  print("\u001Bc")

  val ws = StandaloneAhcWSClient()
  val api = new ApiClient(ws, sys.env.getOrElse("PTE_API_URL", "http://localhost:9000"))
  val user = Await.result(api.newUser(), Duration.Inf)
  playLoop()
  ws.close()
  system.terminate()

  print("\u001B[?47l")


  @tailrec
  private def playLoop(): Unit = {
    play()
    println()
    val again = readBool("Again?", Some(true))
    if (again) {
      println()
      playLoop()
    } else {
      printStats()
    }
  }


  private def printStats(): Unit = {
    val stats = Await.result(api.stats(user), Duration(5, SECONDS))
    println()
    println(s"Played games: ${stats.gamesCount}")
    println(s"Average score: ${stats.averageScore}")
  }


  private def play(): Unit = {
    val (rows, cols) = generateRowsCols(config.rows, config.cols)
    val game = Await.result(api.newGame(rows.size, cols.size), Duration(5, SECONDS))
    println()
    val height = printGame(game, rows, cols)
    println()
    val rs = readRowStrategy(rows)
    val resFuture = api.play(user, game, Play(config.gameType, rs))
    print(s"\u001b[${height + 3}A\n")
    printGame(game, rows, cols, Some(rs), None)
    println("\nYour strategy: " + rows(rs))
    print("Opponent's strategy: ")
    for (i <- 1 to 80 if i <=8 || !resFuture.isCompleted) {
      Thread.sleep(250)
      if (i % 4 == 0) print("\u001b[3D\u001b[0K")
      else print(".")
    }
    val res = Await.result(resFuture, Duration(100, MILLISECONDS))
    print(s"\u001b[${height + 3}A\n")
    printGame(game, rows, cols, Some(rs), Some(res.columnStrategy))
    println("\nYour strategy: " + rows(rs))
    println("Opponent's strategy: " + cols(res.columnStrategy))
    println("Your score: " + game.matrix(rs)(res.columnStrategy)._1)
  }


  private def generateRowsCols(numRows: Int, numCols: Int): (Seq[String], Seq[String]) = {
    val rowFirst = 'A'
    val colFirst = ('A'.toInt + numRows).toChar
    val colEnd = (colFirst.toInt + numCols).toChar

    val rows = (rowFirst until colFirst).map(_.toString)
    val cols = (colFirst until colEnd).map(_.toString)

    (rows, cols)
  }


  private def readRowStrategy(rows: Seq[String]): Int =
    read[Int](
      prompt = "Your strategy",
      converter = { s =>
        val row = rows.indexOf(s.toUpperCase)
        if (row >= 0) Some(row)
        else None
      },
    )


  private def printGame(
    game: Game,
    rows: Seq[String],
    cols: Seq[String],
    highlightRow: Option[Int] = None,
    highlightCol: Option[Int] = None,
  ): Int = {
    val table = ("" +: cols) +:
      game.matrix.zipWithIndex.map {
        case (row: Seq[(Int, Int)], i: Int) => rows(i) +: row.map { case (row: Int, col: Int) => s"$row, $col" }
      }
    val res = TableGenerator.create(table, highlightRow.map(_ + 1), highlightCol.map(_ + 1))
    println(res.string)
    res.height
  }


  private def readBool(prompt: String, default: Option[Boolean]) =
    read(
      prompt,
      default.map(b => (if (b) "yes" else "no", b)),
      Map("yes" -> true, "y" -> true, "no" -> false, "n" -> false).get,
    )


  @tailrec
  private def read[T](
    prompt: String,
    default: Option[(String, T)] = None,
    converter: String => Option[T],
  ): T = {
    val v = readLine(prompt + default.map(p => s" (${p._1})").getOrElse("") + ": ").trim
    val d = if (v.isBlank) default else None
    d match {
      case Some(r) => r._2
      case None =>
        converter(v) match {
          case Some(r) => r
          case None =>
            print("\u001b[1A\033[K")
            read(prompt, default, converter)
        }
    }
  }

}
