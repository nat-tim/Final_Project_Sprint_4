package ru.yandex.praktikum.Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.PageObject.PageObjectHomePage;
import ru.yandex.praktikum.PageObject.PageObjectRegistrationPageRent;
import ru.yandex.praktikum.PageObject.PageObjectRegistrationPageUser;

@RunWith(Parameterized.class)
public class AutoTestOrderScooter {

    private WebDriver driver;

    private final String name;
    private final String familyName;
    private final String address;
    private final String subwayStation;
    private final String phoneNumber;
    private final String whenGets;
    private final int periodRent;
    private final String listCheckboxColour;
    private final String commentForCourier;

    public AutoTestOrderScooter(String name, String familyName, String address, String subwayStation,
                    String phoneNumber, String whenGets, int periodRent, String listCheckboxColour, String commentForCourier) {
        this.name = name;
        this.familyName = familyName;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.whenGets = whenGets;
        this.periodRent = periodRent;
        this.listCheckboxColour = listCheckboxColour;
        this.commentForCourier = commentForCourier;
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Нат", "Тим", "г.Томск,ул.Кочерыжкина, 8", "Черкизовская", "+70000000000", "18.03.2024", 1, "black", "абырвалг"},
                {"Зайка", "Бонзо", "г.Усть-Каменогорск, 8", "Чертановская", "+79999999999", "01.01.2025", 7, "grey", "Оставить за 500 метров от меня"},
                {"Мишка", "Тэдди", "Угу-Ага", "Войковская", "+70010010101", "01.01.3025", 5, "grey", "Обязательно сделать реверанс"},
        };
    }


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
    public void OrderScooterRunWithParameterizedParamsShowsOk() {
        PageObjectHomePage objLoginPage = new PageObjectHomePage(driver);
        objLoginPage.clickButtonOrderUp();
        PageObjectRegistrationPageUser objRegistrationPageUser = new PageObjectRegistrationPageUser(driver);
        objRegistrationPageUser.setUserInfo(name, familyName, address, subwayStation, phoneNumber);
        PageObjectRegistrationPageRent objRegistrationPageRent = new PageObjectRegistrationPageRent(driver);
        objRegistrationPageRent.setRentInfo(whenGets, periodRent, commentForCourier, listCheckboxColour);
        objRegistrationPageRent.AcceptOrder();

    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }

}
