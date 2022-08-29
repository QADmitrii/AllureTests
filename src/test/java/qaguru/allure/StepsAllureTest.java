package qaguru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;


public class StepsAllureTest {

    static String value = "Postman";
    static String linkText = "postmanlabs/postman-app-support";
    static String issue = "Search bar in Environments tab";

    @BeforeAll
    static void config() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserPosition = "0x0";
        Configuration.browserSize = "1920x1080";
    }
    @AfterEach
    void configEnd() {
        closeWebDriver();
    }

    @Test
    @Feature("Поиск")
    @Story("Поиск Issue")
    @Owner("DmitryButov")
    @DisplayName("Поиск Issue в репозитории")


    public void issueSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("/");
        $(".header-search-input").click();
        $(".header-search-input").setValue(value).pressEnter();
        $(linkText(linkText)).click();
        $("#issues-tab").click();
        $("[aria-label='Issues'] div").$(byText(issue)).shouldBe(Condition.visible);
    }


    @Test
    public void issueSearchLambdaStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу GitHub", () -> {
            open("/");;
        });

        step("Ищем нужный репозиторий по запросу " + value, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(value).pressEnter();
        });

        step("Переходим в репозиторий " + linkText, () -> {
            $(linkText(linkText)).click();
        });

        step("Переходим во вкладку Issue", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issue с названием - " + issue, () -> {
            $("[aria-label='Issues'] div").$(byText(issue)).shouldBe(Condition.visible);
        });
    }

    @Test
    public void issueSearchStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        StepsAnnotation steps = new StepsAnnotation();

        steps.openMainPage();
        steps.findRepo(value);
        steps.clickRepo(linkText);
        steps.clickIssue();
        steps.checkIssue(issue);
    }
}
