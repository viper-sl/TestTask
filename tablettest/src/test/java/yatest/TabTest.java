package yatest;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        selectGroup();
        setFilter();
        resultQuantity(48);
        nameEqual();
    }
    @After
    public void exit() {
        driver.quit();
    }

    @Step("Выбор категории")
    private void selectGroup(){
        OpenPage openPage = new OpenPage(driver);
        openPage.homePage("https://yandex.ru/", "Маркет");
        openPage.followLink("Маркет","Компьютеры");
        openPage.followLink("Компьютеры","Планшеты");
        openPage.followLinkTablet();
        openPage.followLink("Перейти ко всем фильтрам","Планшеты");
    }
    @Step("Применение фильтра")
    private void setFilter(){
        FilterPage filter = new FilterPage(driver);
        filter.setPriceFrom("25000");
        filter.setPriceTo("30000");
        filter.allCompanies();
        filter.checkBoxCompany("Acer");
        filter.checkBoxCompany("Lenovo");
        filter.showFilterResult();
    }
    @Step("Проверка количества результатов поиска ({0})")
    private void resultQuantity (int n){
        int products = driver.findElements(By.xpath("//div[contains(@class, 'n-snippet-card2 i-bem b-zone b-spy-visible b-spy-visible_js_inited')]")).size();
        assertEquals("Количество не соответствует",n,products);
    }
    @Step("Проверка соответствия названия 3 товара полученному результату")
    private void nameEqual(){
        String product3name = driver.findElement(By.xpath("(//div[contains(@class, 'n-snippet-card2 i-bem b-zone b-spy-visible b-spy-visible_js_inited')])[3]/div[4]/div[1]/div[1]/a")).getAttribute("title");
        driver.findElement(By.id("header-search")).sendKeys(product3name);
        driver.findElement(By.xpath("//span[@class='search2__button']//button[@role='button']")).click();
        assertEquals("Название не соответствует",product3name, driver.findElement(By.xpath("//h1[@class='title title_size_28 title_bold_yes']")).getText());
    }
}
