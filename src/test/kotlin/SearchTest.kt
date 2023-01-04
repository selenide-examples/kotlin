import com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selectors.by
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
class SearchTest {

    @BeforeAll fun setUp() {
        open("https://duckduckgo.com/")
    }

    @Test
    fun usingDollarsWithBackticks() {
        `$`(By.name("q")).setValue("selenide").pressEnter()
        `$$`(by("data-testid", "result")).shouldHave(sizeGreaterThan(5))
        `$`(by("data-testid", "result")).shouldHave(text("concise ui tests in Java"))
    }

    @Test fun notUsingDollars() {
        element(By.name("q")).setValue("selenide").pressEnter()
        elements(By.cssSelector("[data-testid=\"result\"]")).shouldHave(sizeGreaterThan(5))
        element(By.cssSelector("[data-testid=\"result\"]")).shouldHave(text("concise ui tests in Java"))
    }

    @Test fun usingAliases() {
        get("[name=q]").setValue("selenide").pressEnter()
        all("[data-testid=\"result\"]").shouldHave(sizeGreaterThan(5))
        get("[data-testid=\"result\"]").shouldHave(text("concise ui tests in Java"))
    }

    private fun get(selector: String) : SelenideElement {
        return `$`(selector)
    }
    
    private fun all(selector: String) : ElementsCollection {
        return `$$`(selector)
    }
}