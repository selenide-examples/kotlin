import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.WebElementCondition
import com.codeborne.selenide.WebElementsCondition

fun s(selector: String) : SelenideElement = `$`(selector)
fun ss(selector: String) : ElementsCollection = `$$`(selector)

infix fun ElementsCollection.shouldHave(condition: WebElementsCondition) = this.shouldHave(condition)
infix fun SelenideElement.shouldBe(condition: WebElementCondition) = this.shouldBe(condition)
infix fun SelenideElement.shouldHave(condition: WebElementCondition) = this.shouldHave(condition)
