package core.algorithms

import scala.util.Random


class GameGenerator(random: Random) {

  def apply[T](rows: Seq[T], cols: Seq[T]): Game[T] = {
    val payoffs = (1 to (rows.length * cols.length)).toList
    val rowPayoffs = random.shuffle(payoffs)
    val colPayoffs = random.shuffle(payoffs)
    Game((for {
      r <- rows.indices
      row = for {
        c <- cols.indices
        i = r * cols.length + c
      } yield (cols(c), Payoff(rowPayoffs(i), colPayoffs(i)))
    } yield (rows(r), row.toMap)).toMap)
  }

}

object GameGenerator {

  def apply(random: Random) = new GameGenerator(random)

}
