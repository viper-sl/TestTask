package yatest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class FilterPage {
    private WebDriver driver;

    private final static By FROM_INPUT = By.id("glf-pricefrom-var");
    private final static By TO_INPUT = By.id("glf-priceto-var");
    private final static By ALL_COMPANIES = By.xpath(".//*/div[2]/*/div/*/button");
    private final static By COMPANY_INPUT = By.xpath(".//*/div[2]/div[2]/*/span/span/input");
    private final static String SHOW_BUTTON = "Показать подходящие";

    FilterPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
    }

    @Step("Отображение всех производителей")
    void allCompanies() {
        driver.findElement(ALL_COMPANIES).click();
    }

    @Step("Выбор производителя {0}")
    public void checkBoxCompany(String company){
        driver.findElement(COMPANY_INPUT).sendKeys(company);
        driver.findElement(By.xpath(String.format(".//div[1]/span/label[text()='%s']/..", company))).click();
        driver.findElement(COMPANY_INPUT).clear();
    }

    @Step("Установка цены от {0}")
    public void setPriceFrom (String from){
        driver.findElement(FROM_INPUT).sendKeys(from);
        String valueFrom = driver.findElement(FROM_INPUT).getAttribute("value");
        assertEquals("ошибка ввода значения:  ", from, valueFrom);

    }
    @Step("Установка цены до {0}")
    public void setPriceTo (String to){
        driver.findElement(TO_INPUT).sendKeys(to);
        String valueTo = driver.findElement(TO_INPUT).getAttribute("value");
        assertEquals("ошибка ввода значения:  ", to, valueTo);
    }

    @Step("Отображение результатов фильтра")
    public void showFilterResult(){
        OpenPage openPage = new OpenPage(driver);
        openPage.followLink(SHOW_BUTTON,"Яндекс.Маркет");
    }

}
