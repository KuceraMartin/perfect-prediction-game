package console

case class Table(string: String, width: Int, height: Int)

object TableGenerator {

  private val color = "\u001b[32m"
  private val colorReset = "\u001b[0m"

  private val leftTop = "┌"
  private val innerTop = "┬"
  private val rightTop = "┐"
  private val leftInner = "├"
  private val rightInner = "┤"
  private val innerInner = "┼"
  private val leftBottom = "└"
  private val innerBottom = "┴"
  private val rightBottom = "┘"
  private val horizontal = "─"
  private val vertical = "│"


  def create(table: Seq[Seq[String]], highlightRow: Option[Int] = None, highlightCol: Option[Int] = None): Table = {
    val colSize = table.transpose.map(_.map(_.length).max + 2)
    val header = colSize.map(horizontal.repeat).mkString(leftTop, innerTop, rightTop + "\n")
    val sep = colSize.map(horizontal.repeat).mkString("\n" + leftInner, innerInner, rightInner + "\n")
    val footer = colSize.map(horizontal.repeat).mkString("\n" + leftBottom, innerBottom, rightBottom)
    val centered = table.map(_.zip(colSize).map {
      case (cell: String, len: Int) =>
        val pad = len - cell.length
        val prepend = " ".repeat(pad / 2) + (if (pad % 2 == 1) " " else "")
        (prepend + cell).padTo(len, ' ')
    })
    val hrow = colorRow(centered, highlightRow)
    val hcol = colorRow(hrow.transpose, highlightCol).transpose
    val hcell = (highlightRow, highlightCol) match {
      case (Some(row), Some(col)) => boldCell(hcol, row, col)
      case _ => hcol
    }
    val string = hcell.map(_.mkString(vertical, vertical, vertical)).mkString(header, sep, footer)
    Table(string, colSize.sum, hcol.size * 2 + 1)
  }


  private def colorRow(table: Seq[Seq[String]], row: Option[Int]): Seq[Seq[String]] = {
    row match {
      case Some(row) => table.updated(row, table(row).map(color + _ + colorReset))
      case None => table
    }
  }


  private def boldCell(table: Seq[Seq[String]], row: Int, col: Int): Seq[Seq[String]] = {
    table.updated(row, table(row).updated(col, "\u001b[1m" + table(row)(col) + ""))
  }

}
