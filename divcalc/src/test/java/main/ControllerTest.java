package main;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Класс JUnit тестов класса Controller
 */
public class ControllerTest {
    private Controller contr = new Controller();
    /** Тест верных входных данных */
    @Test
    public void RezultatDiv(){
        assertEquals("2", contr.Rezultat("4","2"));
    }
    /** Тест деления на 0 */
    @Test
    public void RezultatDivZero() throws Exception {
        assertNull(contr.Rezultat("1","0"));
    }
    /** Тест ввода символов */
    @Test
    public void RezultatDivSymbol(){
        assertNull(contr.Rezultat("d","f"));
    }

}
