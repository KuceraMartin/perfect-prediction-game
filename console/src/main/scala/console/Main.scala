package console

import scala.annotation.tailrec
import scala.concurrent.Future
import scala.io.StdIn.readLine
import scala.util.Failure
import scala.util.Try

import akka.actor.ActorSystem
import akka.stream.SystemMaterializer
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import structures.Game


object Main extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  implicit val system = ActorSystem()
  implicit val materializer = SystemMaterializer(system).materializer

  val ws = StandaloneAhcWSClient()

  val numRows = readInt("Number of rows", default = Some(3))
  val numCols = readInt("Number of columns", default = Some(numRows))

  val rowFirst = 'A'
  val colFirst = ('A'.toInt + numRows).toChar
  val colEnd = (colFirst.toInt + numCols).toChar

  val rows = (rowFirst until colFirst).map(_.toString)
  val cols = (colFirst until colEnd).map(_.toString)

  val api = new ApiClient(ws)

  play()
    .andThen { _ =>
      ws.close()
      system.terminate()
    }
    .andThen {
      case Failure(exception) =>
        println("Error:")
        exception.printStackTrace()
        System.exit(1)
    }


  private def play(): Future[Unit] = {
    for {
      user <- api.newUser()
      game <- api.newGame(numRows, numCols)
      _ <- printGame(game)
      rs <- readRowStrategy()
      res <- api.play(user, game, rs)
    } yield {
      println("Opponent's strategy: " + cols(res.columnStrategy))
    }
  }

  private def readRowStrategy(): Future[Int] = {
    @tailrec
    def loop(prompt: String): Int = {
      val row = rows.indexOf(readLine(prompt))
      if (row >= 0) row
      else loop("Please choose a valid strategy: ")
    }
    Future(loop("Choose your strategy: "))
  }

  private def printGame(game: Game): Future[Unit] = Future {
    val w = (
        rows.map(_.length) ++
        cols.map(_.length) ++
        game.matrix.flatten.map(_.toString.length)
      ).max
    print("\t")
    for (col <- cols) {
      print(col.padTo(w, ' ') + "\t")
    }
    println()
    for (i <- 0 until numRows) {
      print(s"${rows(i)}\t")
      print(game.matrix(i).map(_.toString.padTo(w, ' ')).mkString("\t"))
      println()
    }
  }

  private def readInt(prompt: String, default: Option[Int]) = read(prompt, default, s => Try(s.toInt).toOption)

  @tailrec
  private def read[T](
    prompt: String,
    default: Option[T] = None,
    converter: String => Option[T]
  ): T = {
    val v = readLine(prompt + default.map(p => s" ($p)").getOrElse("") + ":")
    val d = if (v.isBlank) default else None
    d match {
      case Some(r) => r
      case None =>
        converter(v) match {
          case Some(r) => r
          case None => read(prompt, default, converter)
        }
    }
  }

}
