package yatest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.allure.annotations.Step;
import yatest.Actions.Verification;
import yatest.Page.FilterPage;
import yatest.Page.OpenPage;


public class CoreTest {
    public static WebDriver driver;
    @Step("Выбор категории")
    public void selectGroup(String group){
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
    public void setFilter(String from, String to, String ... company){
        FilterPage filter = new FilterPage(driver);
        filter.setPriceFrom(from);
        filter.setPriceTo(to);
        filter.allCompanies();
        for (int i=0; i<company.length; i++){
            filter.checkBoxCompany(company[i]);
        }
        filter.showFilterResult();
    }
    @Step("Проверки")
    public void verify(){
        Verification verify = new Verification(driver);
        verify.resultQuantity(48);
        verify.nameEqual();
        verify.offers();

    }

}
