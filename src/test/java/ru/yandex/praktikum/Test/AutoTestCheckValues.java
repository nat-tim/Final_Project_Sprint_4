package ru.yandex.praktikum.Test;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.PageObject.PageObjectHomePage;

import java.util.Arrays;
import java.util.List;

public class AutoTestCheckValues {

    private WebDriver driver;


    @Before
    public void setup() {
        String setbrowser = "chrome"; //firefox
        if (setbrowser.equals("chrome")){
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.gecko.driver", "E:\\WebDriver\\bin\\geckodriver.exe");
            //System.setProperty("webdriver.firefox.marionette", "E:\\WebDriver\\bin\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }



    @Test
    public void checkFaqShowsOk() {


        // создай объект класса страницы авторизации
        PageObjectHomePage objLoginPage = new PageObjectHomePage(driver);
        List<String> valueOfText = Arrays.asList("Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, " +
                        "можете просто сделать несколько заказов — один за другим.", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                        "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая " +
                        "в 20:30, суточная аренда закончится 9 мая в 20:30.", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься" +
                        " без передышек и во сне. Зарядка не понадобится.", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "Да, обязательно. Всем самокатов! И Москве, и Московской области.");

        objLoginPage.checkFaqText(valueOfText);

    }


    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }

}
