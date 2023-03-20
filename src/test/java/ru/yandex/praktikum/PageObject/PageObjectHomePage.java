package ru.yandex.praktikum.PageObject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PageObjectHomePage {

    private By faq = By.xpath(".//div[contains(@class,'accordion__heading')]");
    private By faqText = By.xpath(".//div[contains(@class,'accordion__heading')]/following-sibling::div[not(@hidden)]/p");
    private By buttonOrderUp = By.xpath(".//button[@class='Button_Button__ra12g']");
    private By buttonOrderDown = By.xpath("..//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private WebDriver driver;

    public PageObjectHomePage(WebDriver driver){
        this.driver = driver;
    }

    public void checkFaqText(List <String> valueOfText) {
        //скролл до элемента FQA
        WebElement element = driver.findElement(faq);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        int xpathCount= driver.findElements(faq).size();
        //список элементов для проверки текста  FQA
        List <WebElement> faqButton = driver.findElements(By.xpath(faq.toString().substring(10) +"/div[contains(@id,'accordion__heading')]"));

        //цикл для сравнения элементов текста FQA с определенными значениями
        for (int i = 0; i< xpathCount;i++) {
            //скролл до элемента который необходимо открыть
            WebElement element1 = faqButton.get(i);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element1);
            faqButton.get(i).click();
            WebElement element2 = driver.findElement(faqText);
            //ожидание появления текста
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOf(element2));
            String valueOfTextFQA = element2.getText();
            Assert.assertTrue("Текст элемента " + (i+1) + " не соответствует заявленному", valueOfTextFQA.equals(valueOfText.get(i)));
        }
    }

    //клик по кнопке сделать заказ
    public void clickButtonOrderUp() { driver.findElement(buttonOrderUp).click(); }

    public void clickButtonOrderDown() { driver.findElement(buttonOrderDown).click();  }


}
