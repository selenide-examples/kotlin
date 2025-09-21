import com.codeborne.selenide.Selectors.byText

object SelenideOrgPage {
  private val mainMenuPages get() = s(".main-menu-pages")

  fun openPage(page: String) {
    mainMenuPages.find(byText(page)).click()
  }
}
