import com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.SelenideElement
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.By

/*
 * I tried to create a sample Selenide test in Kotlin, but encountered few problems:
 * 
 * 1. Selenide.$(By.name("q")); // $ doesn't compile in Kotlin :(
 * 2. Selenide.getElement(By.name("q")).val("selenide+kotlin"); // val doesn't compile in Kotlin :(
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GoogleTest {

    @BeforeAll fun setUp() {
        Configuration.startMaximized = false
        open("https://google.com/ncr")
    }

    @Test
    fun usingDollarsWithBackticks() {
        `$`(By.name("q")).setValue("selenide").pressEnter()
        `$$`("#res .g").shouldHave(sizeGreaterThan(5))
        `$`("#res .g").shouldHave(text("concise ui tests in Java"))
    }

    @Test fun notUsingDollars() {
        element(By.name("q")).setValue("selenide").pressEnter()
        elements(By.cssSelector("#res .g")).shouldHave(sizeGreaterThan(5))
        element(By.cssSelector("#res .g")).shouldHave(text("concise ui tests in Java"))
    }

    @Test fun usingAliases() {
        get("[name=q]").setValue("selenide").pressEnter()
        all("#res .g").shouldHave(sizeGreaterThan(5))
        get("#res .g").shouldHave(text("concise ui tests in Java"))
    }

    fun get(selector: String) : SelenideElement {
        return `$`(selector)
    }
    
    fun all(selector: String) : ElementsCollection {
        return `$$`(selector)
    }
}