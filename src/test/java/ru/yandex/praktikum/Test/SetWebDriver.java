package ru.yandex.praktikum.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SetWebDriver {
    public WebDriver setupDriver() {
        WebDriver driver;
        String setbrowser = "chrome"; //firefox, chrome
        if (setbrowser.equals("chrome")){
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        return driver;
    }
}
