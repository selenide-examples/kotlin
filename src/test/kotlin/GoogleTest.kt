import com.codeborne.selenide.CollectionCondition.size
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.Selenide.getElement
import com.codeborne.selenide.Selenide.getElements
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.SelenideElement
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.By

/*
 * I tried to create a sample Selenide test in Kotlin, but encountered few problems:
 * 
 * 1. Selenide.$(By.name("q")); // $ doesn't compile in Kotlin :(
 * 2. Selenide.getElement(By.name("q")).val("selenide+kotlin"); // val doesn't compile in Kotlin :(
 */
public class GoogleTest {

    @Before fun setUp() {
        open("http://google.com/en")
    }
    
    @Test fun usingDollarsWithBackticks() {
        `$`(By.name("q")).setValue("selenide").pressEnter()
        `$$`("#ires .g").shouldHave(size(10))
        `$`("#ires .g").shouldHave(text("concise ui tests in Java"));
    }

    @Test fun notUsingDollars() {
        getElement(By.name("q")).setValue("selenide").pressEnter()
        getElements(By.cssSelector("#ires .g")).shouldHave(size(10))
        getElement(By.cssSelector("#ires .g")).shouldHave(text("concise ui tests in Java"));
    }

    @Test fun usingAliases() {
        get("[name=q]").setValue("selenide").pressEnter()
        all("#ires .g").shouldHave(size(10))
        get("#ires .g").shouldHave(text("concise ui tests in Java"));
    }

    fun get(selector: String) : SelenideElement {
        return `$`(selector);
    }
    
    fun all(selector: String) : ElementsCollection {
        return `$$`(selector);
    }
}