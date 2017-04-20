import org.scalatest.{Matchers, WordSpecLike}

class CafeMenuSpec extends WordSpecLike with Matchers {

  "Cafe Menu" should {
    "list Cola correctly" in {
      CafeMenu.items should contain ("Cola" -> MenuItem("Cola", COLD, 50))
    }
    "list Coffee correctly" in {
      CafeMenu.items should contain ("Coffee" -> MenuItem("Coffee", HOT, 100))
    }
    "list Cheese Sandwich correctly" in {
      CafeMenu.items should contain ("Cheese Sandwich" -> MenuItem("Cheese Sandwich", COLD, 200))

    }
    "list Steak Sandwich correctly" in {
      CafeMenu.items should contain ("Steak Sandwich" -> MenuItem("Steak Sandwich", HOT, 450))
    }
  }

  "Cafe Menu billing" should {
    "generate correct bill amount for two coffees and one Cola" in {
      CafeMenu.billAmount(List("Coffee", "Coffee", "Cola")) should be ("2.5")
    }
    "generate correct bill amount for one coffees,  one Cola and one Cheese Sandwich" in {
      CafeMenu.billAmount(List("Coffee", "Cheese Sandwich", "Cola")) should be ("3.5")
    }
  }

  "Cafe Menu" should {
    "Apply no service tax for drinks only order" in {
      CafeMenu.serviceCharge(List("Coffee", "Cola")) should be(0)
    }
    
  }
}
