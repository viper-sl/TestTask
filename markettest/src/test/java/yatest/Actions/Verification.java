package yatest.Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class Verification  {
    WebDriver driver;
    public Verification(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Проверка количества результатов поиска ({0})")
    public void resultQuantity (int n){
        int products = driver.findElements(By.xpath("//div[contains(@class, 'n-snippet-card2 i-bem b-zone b-spy-visible b-spy-visible_js_inited')]")).size();
        assertEquals("Количество не соответствует",n,products);
    }
    @Step("Проверка соответствия названия 1 товара полученному результату")
    public void nameEqual(){
        String productname = driver.findElement(By.xpath("(//div[contains(@class, 'n-snippet-card2 i-bem b-zone b-spy-visible b-spy-visible_js_inited')])[1]/div[4]/div[1]/div[1]/a")).getAttribute("title");
        driver.findElement(By.id("header-search")).sendKeys(productname);
        driver.findElement(By.xpath("//span[@class='search2__button']//button[@role='button']")).click();
        assertThat(driver.findElement(By.xpath("//h1[@class='title title_size_28 title_bold_yes']")).getText(), containsString(productname));
    }
    @Step("Количество доступных торговых площадок в разделе «Предложения Магазинов»")
    public void offers(){
        int offersCount = driver.findElements(By.xpath("//html//div[@class='n-product-top-offers-list__body']/div")).size();
        assertNotNull("Количество торговый площадок не подсчитано",offersCount);
        System.out.println("Количество торговых площадок: " + offersCount);
    }
}
