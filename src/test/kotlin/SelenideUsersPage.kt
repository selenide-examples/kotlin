import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible

object SelenideUsersPage {
  private val tags get() = ss("#user-tags .tag")
  private val users get() = ss("#selenide-users .user")
  val shownUsers get() = users.filter(visible)

  fun filterByTag(tag: String) {
    tags.findBy(text(tag)).click()
  }
}
