import com.codeborne.selenide.CollectionCondition.*
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selectors.byText
import com.codeborne.selenide.Selenide.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/*
 * I tried to create a sample Selenide test in Kotlin, but encountered few problems:
 *
 * 1. Selenide.$(By.name("q")); // $ doesn't compile in Kotlin :(
 * 2. Selenide.getElement(By.name("q")).val("selenide+kotlin"); // val doesn't compile in Kotlin :(
 */
class SelenideUsersTest {

    @BeforeEach fun setUp() {
        open("https://selenide.org/")
    }

    @Test
    fun `using dollars with backticks`() {
        `$`(".main-menu-pages") shouldBe visible
        `$`(".main-menu-pages").find(byText("Users")).click()
        `$$`("#selenide-users .user") shouldHave sizeGreaterThan(150)

        `$$`("#user-tags .tag") shouldHave sizeGreaterThan(10)
        `$$`("#user-tags .tag").findBy(text("ukraine")).click()
        `$$`("#selenide-users .user").filter(visible).shouldHave(
            sizeGreaterThan(15),
            sizeLessThan(100)
        )
    }

    @Test fun `using methods 'element' and 'elements'`() {
        element(".main-menu-pages").find(byText("Users")).click()
        elements("#user-tags .tag").findBy(text("australia")).click()
        elements("#selenide-users .user").filter(visible).shouldHave(size(1))
    }

    @Test fun `using aliases`() {
        s(".main-menu-pages").find(byText("Users")).click()
        ss("#user-tags .tag").findBy(text("finances")).click()
        ss("#selenide-users .user").filter(visible).shouldHave(
            sizeGreaterThan(40),
            sizeLessThan(60)
        )
    }

    @Test fun `using page object`() {
      SelenideOrgPage.apply {
        openPage("Users")
      }

      SelenideUsersPage.apply {
        filterByTag("finances")

        shownUsers.shouldHave(
          sizeGreaterThan(40),
          sizeLessThan(60)
        )
      }
    }
}
