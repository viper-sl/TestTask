package yatest.Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;
import yatest.Actions.BrowserStart;
import yatest.CoreTest;

@Title("Тестовый сценарий 'Планшеты'")
public class TabletTest extends CoreTest {
    @Before
    public  void start() {
        driver = BrowserStart.startFirefox(driver);
    }
    @After
    public void exit() {
        driver.quit();
    }
    @Test
    public void tabletsite(){
        selectGroup("Планшеты");
        setFilter("20000","25000","ASUS","Lenovo");
        verify();
    }
}
