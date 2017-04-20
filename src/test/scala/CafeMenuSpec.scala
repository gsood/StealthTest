import org.scalatest.{Matchers, WordSpecLike}

class CafeMenuSpec extends WordSpecLike with Matchers {

  "Cafe Menu" should {
    "list Cola correctly" in {
      CafeMenu.items should contain ("Cola" -> Drink("Cola", COLD, 50))
    }
    "list Coffee correctly" in {
      CafeMenu.items should contain ("Coffee" -> Drink("Coffee", HOT, 100))
    }
    "list Cheese Sandwich correctly" in {
      CafeMenu.items should contain ("Cheese Sandwich" -> Food("Cheese Sandwich", COLD, 200))

    }
    "list Steak Sandwich correctly" in {
      CafeMenu.items should contain ("Steak Sandwich" -> Food("Steak Sandwich", HOT, 450))
    }
  }

  "Cafe Menu billing" should {
    "generate correct bill amount for two coffees and one Cola" in {
      CafeMenu.totalBillAmt(List("Coffee", "Coffee", "Cola")) should be ("2.50")
    }
    "generate correct bill amount for one coffees,  one Cola and one Cheese Sandwich" in {
      CafeMenu.totalBillAmt(List("Coffee", "Cheese Sandwich", "Cola")) should be ("3.85")
    }
  }

  "Cafe Menu" should {
    "Apply no service tax for drinks only order" in {
      CafeMenu.totalBillAmt(List("Coffee", "Coffee", "Cola", "Cola")) should be ("3.00")
    }

    "Apply 10% service tax for cold food  order" in {
      CafeMenu.totalBillAmt(List("Coffee", "Cola", "Cheese Sandwich")) should be("3.85")
    }

    "Apply 20% service tax for hot food  order" in {
      CafeMenu.totalBillAmt(List("Coffee", "Cola", "Steak Sandwich")) should be("7.20")
    }

    "Apply 20% service tax for hot food order but limit service charge to £20" in {
      CafeMenu.totalBillAmt((1 to 100).map(_ => "Steak Sandwich").toList) should be("470.00")
    }
  }
}
