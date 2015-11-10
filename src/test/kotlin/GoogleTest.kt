import com.codeborne.selenide.CollectionCondition.size
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.Selenide.getElement
import com.codeborne.selenide.Selenide.getElements
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.By


// Selenide.$(By.name("q")); // $ doesn't compile in Kotlin :(
// Selenide.getElement(By.name("q")).val("selenide+kotlin"); // val doesn't compile in Kotlin :(
public class GoogleTest {

    @Before fun setUp() {
        open("http://google.com/en")
    }
    
    @Test fun usingDollarsWithApostrophes() {
        Selenide.`$`(By.name("q")).setValue("selenide").pressEnter()
        Selenide.`$$`("#ires .g").shouldHave(size(10))
        Selenide.`$`("#ires .g").shouldHave(text("concise ui tests in Java"));
    }

    @Test fun usingDollarsWithStaticImports() {
        `$`(By.name("q")).setValue("selenide").pressEnter()
        `$$`("#ires .g").shouldHave(size(10))
        `$`("#ires .g").shouldHave(text("concise ui tests in Java"));
    }

    @Test fun notUsingDollars() {
        Selenide.getElement(By.name("q")).setValue("selenide").pressEnter()
        Selenide.getElements(By.cssSelector("#ires .g")).shouldHave(size(10))
        Selenide.getElement(By.cssSelector("#ires .g")).shouldHave(text("concise ui tests in Java"));
    }

    @Test fun notUsingDollarsWithStaticImports() {
        getElement(By.name("q")).setValue("selenide").pressEnter()
        getElements(By.cssSelector("#ires .g")).shouldHave(size(10))
        getElement(By.cssSelector("#ires .g")).shouldHave(text("concise ui tests in Java"));
    }
}