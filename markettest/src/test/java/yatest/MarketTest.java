package yatest;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

@Title("Тестовые сценарии")
public class MarketTest {
    private static WebDriver driver;

    @After
    public void exit() {
        driver.quit();
    }
    @Title("Тестовый сценарий 'Ноутбуки'")
    @Test
    public void laptopsite(){
        driver = BrowserStart.startChrome(driver);
        selectGroup("Ноутбуки");
        setFilter("","30000","HP","Lenovo");
        resultQuantity(48);
        nameEqual();
        offers();
    }
   @Title("Тестовый сценарий 'Планшеты'")
    @Test
    public void tabletsite(){
        driver = BrowserStart.startFirefox(driver);
        selectGroup("Планшеты");
        setFilter("20000","25000","ASUS","Lenovo");
        resultQuantity(48);
        nameEqual();
        offers();
    }
    @Step("Выбор категории")
    private void selectGroup(String group){
        OpenPage openPage = new OpenPage(driver);
        openPage.homePage("https://yandex.ru/", "Маркет");
        openPage.followLink("Маркет","Компьютеры");
        openPage.followLink("Компьютеры",group);
        if (driver instanceof FirefoxDriver){
            openPage.followLinkTablet();
        }
        else{
            openPage.followLink(group,"Перейти ко всем фильтрам");
        }
        openPage.followLink("Перейти ко всем фильтрам",group);
    }
    @Step("Применение фильтра")
    private void setFilter(String from, String to, String ... company){
        FilterPage filter = new FilterPage(driver);
        filter.setPriceFrom(from);
        filter.setPriceTo(to);
        filter.allCompanies();
        for (int i=0; i<company.length; i++){
            filter.checkBoxCompany(company[i]);
        }
        filter.showFilterResult();
    }
    @Step("Проверка количества результатов поиска ({0})")
    private void resultQuantity (int n){
        int products = driver.findElements(By.xpath("//div[contains(@class, 'n-snippet-card2 i-bem b-zone b-spy-visible b-spy-visible_js_inited')]")).size();
        assertEquals("Количество не соответствует",n,products);
    }
    @Step("Проверка соответствия названия 1 товара полученному результату")
    private void nameEqual(){
        String productname = driver.findElement(By.xpath("(//div[contains(@class, 'n-snippet-card2 i-bem b-zone b-spy-visible b-spy-visible_js_inited')])[1]/div[4]/div[1]/div[1]/a")).getAttribute("title");
        driver.findElement(By.id("header-search")).sendKeys(productname);
        driver.findElement(By.xpath("//span[@class='search2__button']//button[@role='button']")).click();
        assertThat(driver.findElement(By.xpath("//h1[@class='title title_size_28 title_bold_yes']")).getText(), containsString(productname));
    }
    @Step("Количество доступных торговых площадок в разделе «Предложения Магазинов»")
    private void offers(){
        int offersCount = driver.findElements(By.xpath("//html//div[@class='n-product-top-offers-list__body']/div")).size();
        assertNotNull("Количество торговый площадок не подсчитано",offersCount);
        System.out.println("Количество торговых площадок: " + offersCount);
    }
}
