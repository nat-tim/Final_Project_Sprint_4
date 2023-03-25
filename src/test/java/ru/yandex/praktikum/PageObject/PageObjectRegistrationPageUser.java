package ru.yandex.praktikum.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObjectRegistrationPageUser {


    private By name = By.xpath(".//input[@placeholder='* Имя']");
    private By familyName = By.xpath(".//input[@placeholder='* Фамилия']");
    private By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By subwayStation = By.xpath(".//input[@placeholder='* Станция метро']");
    private By phoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private WebDriver driver;

    public PageObjectRegistrationPageUser(WebDriver driver){
        this.driver = driver;
    }

    //заполнить поле имя
    public void setName(String name) {
        driver.findElement(this.name).sendKeys(name);
    }
    //заполнить поле фамилия
    public void setFamilyName(String familyName) {
        driver.findElement(this.familyName).sendKeys(familyName);
    }
    //выбрать адрес
    public void setAddress(String address) {
        driver.findElement(this.address).sendKeys(address);
    }
    //выбрать станцию метро
    public void setSubwayStation(String subwayStation) {

        driver.findElement(this.subwayStation).click();
        driver.findElement(this.subwayStation).sendKeys(subwayStation);
        String subwayStationSelected = String.format(".//div[@class='select-search__select']//*[text()='%s']", subwayStation);
        driver.findElement(By.xpath(subwayStationSelected)).click();

    }
    //передать номер телефона
    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(this.phoneNumber).sendKeys(phoneNumber);
    }
    //клик по кнопке далее
    public void clickButtonNext() {
        WebElement element = driver.findElement(buttonNext);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    public void setUserInfo(String name, String familyName, String address, String subwayStation, String phoneNumber){
        setName(name);
        setFamilyName(familyName);
        setAddress(address);
        setSubwayStation(subwayStation);
        setPhoneNumber(phoneNumber);
        clickButtonNext();
    }


}
