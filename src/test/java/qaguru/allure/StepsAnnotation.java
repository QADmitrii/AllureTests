package qaguru.allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;



public class StepsAnnotation {

    @Step("Открываем главную страницу GitHub")
    public void openMainPage() {
        open("/");;
    }

    @Step("Ищем нужный репозиторий по запросу {value}")
    public void findRepo(String value) {
        $(".header-search-input").click();
        $(".header-search-input").setValue(value).pressEnter();
    }

    @Step("Переходим в репозиторий {repo}")
    public void clickRepo(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Переходим во вкладку Issue")
    public void clickIssue() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с названием {issue}")
    public void checkIssue(String issue) {
        $("[aria-label='Issues'] div").$(byText(issue)).shouldBe(Condition.visible);
    }
}
