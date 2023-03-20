package ru.yandex.praktikum.PageObject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectRegistrationPageRent {


    private By whenGets = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By periodRent = By.xpath(".//span[@class='Dropdown-arrow']");
    //private By listCheckboxColour = By.xpath(".//input[@class='Checkbox_Input__14A2w']");
    private By commentForCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By buttonOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By confOfOrder = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    private WebDriver driver;

    public PageObjectRegistrationPageRent(WebDriver driver){
        this.driver = driver;
    }
    //выбрать дату доставки
    public void setWhenGets(String whenGets) {
        WebElement element = driver.findElement(this.whenGets);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(whenGets);
    }
    //выбрать период аренды
    public void setPeriodRent(int periodRentSelect) {
        driver.findElement(this.periodRent).click();
        String periodRentSelected = String.format(".//div[@class='Dropdown-control']/following-sibling::div/div[%d]", periodRentSelect);
        driver.findElement(By.xpath(periodRentSelected)).click();
    }
    //выбрать цвет самоката
    public void clickCheckboxColour(String colour) {
        String colourSelected = String.format(".//input[@id='%s']", colour);
        WebElement element = driver.findElement(By.xpath(colourSelected));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    //оставить комментарий для курьера
    public void setCommentForCourier(String commentForCourier) {
        driver.findElement(this.commentForCourier).sendKeys(commentForCourier);
    }
    //клик по кнопке заказа
    public void clickButtonOrder() {
        driver.findElement(buttonOrder).click();
    }
    //клик по кнопки подтверждения
    public void clickButtonYes() {
        WebElement element = driver.findElement(this.buttonYes);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    //ожидание появления кнопки подтверждения
    public void waitForLoadButtonYes() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(buttonYes).getText() != null
                && !driver.findElement(buttonYes).getText().isEmpty()
        ));
    }
    //ожидание появления кнопки сделать заказ
    public void waitForLoadConfOfOrder() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(confOfOrder).getText() != null
                && !driver.findElement(confOfOrder).getText().isEmpty()
        ));
    }
    //проверка того,что заказ оформлен
    public void AcceptOrder(){
        waitForLoadButtonYes();
        clickButtonYes();
        waitForLoadConfOfOrder();
        String textOfBlock = driver.findElement(confOfOrder).getText();
        Assert.assertTrue("Заказ не зарегистрирован", textOfBlock.equals("Заказ оформлен"));
    }
    public void setRentInfo(String whenGets, int periodRentSelect, String commentForCourier, String colour){
        setWhenGets(whenGets);
        setPeriodRent(periodRentSelect);
        clickCheckboxColour(colour);
        setCommentForCourier(commentForCourier);
        clickButtonOrder();

    }


}
