import java.text.DecimalFormat

object CafeMenu {

  var items = Map[String, MenuItem](
    "Cola" -> Drink("Cola", COLD, 50),
    "Coffee" -> Drink("Coffee", HOT, 100),
    "Cheese Sandwich" -> Food("Cheese Sandwich", COLD, 200),
    "Steak Sandwich" -> Food("Steak Sandwich", HOT, 450)
  )


  def totalBillAmt(orderedItems: List[String]) = {
    var o = orderedMenuItems(orderedItems)
    val amt: Int = billAmount(o)
    formatPence(amt + serviceChargeAmt(o, amt))
  }

  private def serviceChargeAmt(orderedItems: List[MenuItem], billAmt:Int): Int = {
    val per = serviceChargePer(orderedItems)
    val amt: Float = billAmt * (per.toFloat / 100)
    if(per == 20 && amt > 2000) 2000
    else amt.toInt
  }

  private def serviceChargePer(orderedItems: List[MenuItem]): Int = {
    val pers = orderedItems.map(_ match {
      case Food(_, COLD, _) => 10
      case Food(_, HOT, _) => 20
      case _ => 0
    })
    if(pers.contains(20)) 20
    else if(pers.contains(10)) 10
    else 0

  }

  var formatter:DecimalFormat = new DecimalFormat("#.00")

  def billAmount(orderedItems: List[MenuItem]) =
    orderedItems.map(_.priceInPence).sum

  private def formatPence(n:Int): String = {
    formatter.format(n.toFloat / 100)
  }

  private def orderedMenuItems(orderedItems:List[String]):List[MenuItem] =
    orderedItems.map(i => items.get(i)).filter(_.isDefined).map(_.get)
}

trait MenuItem {
  def priceInPence:Int
}
case class Food(name:String, category:Category, priceInPence: Int) extends MenuItem
case class Drink(name:String, category:Category, priceInPence: Int) extends MenuItem

trait Category

case object HOT extends Category
case object COLD extends Category


