package yatest.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

public class OpenPage {
    private WebDriver driver;

    public OpenPage(WebDriver driver) { this.driver = driver; }

    @Step("Переход по кнопке {0}, проверка наличия: {1}")
    public void followLink(String linkText, String linkTextWait){
        driver.findElement(By.linkText(linkText)).click();
        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkTextWait)));

    }
    @Step("Переход по кнопке Планшеты")
    public void followLinkTablet (){
        WebElement linkButton = driver.findElement(By.xpath("//a[@class='link catalog-menu__list-item metrika i-bem metrika_js_inited'][contains(text(),'Планшеты')]"));
        linkButton.click();
        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Планшеты")));
    }
    @Step("Открытие страницы {0}")
    public void homePage (String page, String linkTextWait){
        driver.get(page);
        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkTextWait)));
    }

}
