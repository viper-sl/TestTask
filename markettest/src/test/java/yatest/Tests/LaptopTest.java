package yatest.Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;
import yatest.Actions.BrowserStart;
import yatest.CoreTest;

@Title("Тестовый сценарий 'Ноутбуки'")
public class LaptopTest extends CoreTest {
    @Before
    public  void start() {
        driver = BrowserStart.startChrome(driver);
    }
    @After
    public void exit() {
        driver.quit();
    }
    @Test
    public void laptopsite(){
        selectGroup("Ноутбуки");
        setFilter("","30000","HP","Lenovo");
        verify();
    }



}
