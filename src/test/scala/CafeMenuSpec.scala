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
      CafeMenu.generateBill(List("Coffee", "Coffee", "Cola")) should be (350)
    }
  }

}
