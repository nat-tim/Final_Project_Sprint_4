package ru.yandex.praktikum.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.PageObject.PageObjectHomePage;


@RunWith(Parameterized.class)
public class AutoTestCheckValues extends SetWebDriver {

    private WebDriver driver;

    private final String textFQA;
    private final boolean result;
    private final int numberElementFQA;

    public AutoTestCheckValues(String textFQA, boolean result, int numberElementFQA) {
        this.textFQA = textFQA;
        this.result = result;
        this.numberElementFQA = numberElementFQA;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", true, 1},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями," +
                        " можете просто сделать несколько заказов — один за другим.", true, 2},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                        "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая " +
                        "в 20:30, суточная аренда закончится 9 мая в 20:30.", true, 3},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", true, 4},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", true, 5},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься" +
                        " без передышек и во сне. Зарядка не понадобится.", true, 6},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", true, 7},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", true, 8},
        };
    }

    @Before
    public void setup() {
        driver = setupDriver();

        driver.get("https://qa-scooter.praktikum-services.ru/");
    }



    @Test
    public void checkFaqShowsOk() {

        // создание объекта класса страницы авторизации
        PageObjectHomePage objLoginPage = new PageObjectHomePage(driver);

        objLoginPage.checkFaqText(textFQA, result, numberElementFQA);


    }


    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }

}
