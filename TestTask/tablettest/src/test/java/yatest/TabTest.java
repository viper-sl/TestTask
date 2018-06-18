package yatest;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@Title("Тестовый сценарий 'Планшеты'")
public class TabTest {
    private static WebDriver driver;
    @Before
    public void start(){
        FirefoxDriverManager.getInstance().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
    }
    @Test
    public void tabsite(){
        homePage("https://yandex.ru/", "Маркет");
        followLink("Маркет","Компьютеры");
        followLink("Компьютеры","Планшеты");
        followLinkTablet();
        followLink("Перейти ко всем фильтрам","Планшеты");
        fillText("glf-pricefrom-var","25000");
        fillText("glf-priceto-var","30000");
        driver.findElement(By.xpath(".//*/div[2]/*/div/*/button")).click();
        checkBoxCompany("Acer");
        checkBoxCompany("Lenovo");
        followLink("Показать подходящие", "Планшеты");
        resultQuantity(48);
        nameEqual();
    }
    @After
    public void exit() {
        driver.quit();
    }
    @Step("Открытие страницы Яндекс")
    private void homePage (String page, String linkTextWait){
        driver.get(page);
        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkTextWait)));
    }
    @Step("Переход по кнопке {0}, проверка наличия: {1}")
    private void followLink (String linkText, String linkTextWait){
        WebElement linkButton = driver.findElement(By.linkText(linkText));
        linkButton.click();
        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkTextWait)));
    }
    @Step("Переход по кнопке Планшеты")
    private void followLinkTablet (){
        WebElement linkButton = driver.findElement(By.xpath("//a[@class='link catalog-menu__list-item metrika i-bem metrika_js_inited'][contains(text(),'Планшеты')]"));
        linkButton.click();
        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Планшеты")));
    }
    @Step("Ввод текста: {1}")
    private void fillText(String id, String text){
        WebElement inputField = driver.findElement(By.id(id));
        inputField.sendKeys(text);
        String value = inputField.getAttribute("value");
        assertEquals("ошибка ввода значения:  ", value, text);

    }
    @Step("Выбор производителя {0}")
    private void checkBoxCompany(String company){
        By companyInput = By.xpath(".//*/div[2]/div[2]/*/span/span/input");
        driver.findElement(companyInput).sendKeys(company);
        driver.findElement(By.xpath(String.format(".//div[1]/span/label[text()='%s']/..", company))).click();
        driver.findElement(companyInput).clear();
    }
    @Step("Проверка количества результатов поиска ({0})")
    private void resultQuantity (int n){
        int products = driver.findElements(By.xpath("//div[contains(@class, 'n-snippet-card2 i-bem b-zone b-spy-visible b-spy-visible_js_inited')]")).size();
        assertEquals("Количество не соответствует",n,products);
    }
    @Step("Проверка соответствия названия 3 товара полученному результату")
    private void nameEqual(){
        String product3name = driver.findElement(By.xpath("(//div[contains(@class, 'n-snippet-card2 i-bem b-zone b-spy-visible b-spy-visible_js_inited')])[3]/div[4]/div[1]/div[1]/a")).getAttribute("title");
        fillText("header-search",product3name);
        driver.findElement(By.xpath("//span[@class='search2__button']//button[@role='button']")).click();
        assertEquals("Название не соответствует",product3name, driver.findElement(By.xpath("//h1[@class='title title_size_28 title_bold_yes']")).getText());
    }
}
