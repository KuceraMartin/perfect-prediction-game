package console

object TableGenerator {

  def create(table: Seq[Seq[String]]): String = {
    val colSize = table.transpose.map(_.map(_.length).max + 2)
    val header = colSize.map("─".repeat).mkString("┌", "┬", "┐\n")
    val sep = colSize.map("─".repeat).mkString("\n├", "┼", "┤\n")
    val footer = colSize.map("─".repeat).mkString("\n└", "┴", "┘")
    table.map(_.zip(colSize).map {
      case (cell: String, len: Int) =>
        val pad = len - cell.length
        val prepend = " ".repeat(pad / 2) + (if (pad % 2 == 1) " " else "")
        (prepend + cell).padTo(len, ' ')
    }.mkString("│", "│", "│"))
     .mkString(header, sep, footer)
  }

}
