package main;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Класс управления функциями калькулятора
 */
public class Controller {
    /** Объявление переменных */
    private double x;
    private double y;
    private double z;
    private String sA;
    private String sB;
    private String sC;
    /** Привязка к GUI */
    @FXML
    Button btn1;
    @FXML
    TextField A;
    @FXML
    TextField B;
    @FXML
    TextField C;

    /**
     * Метод вызываемый нажатием кнопки
     */
    public void Action(){

        sA=A.getText();
        sB=B.getText();
        C.setText(Rezultat(sA,sB));
    }

    /**
     * Метод расчета результата деления
     * @param sA Делимое
     * @param sB Делитель
     * @return Возвращает частное от деления
     */
    public String Rezultat(String sA, String sB){
        /** Настройка формата вывода и округления */
        java.text.DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        /**
         *  Попытка преобразования в Double, проверка деления на 0, деление.
         */
        try {
            x = Double.parseDouble(sA);
            y = Double.parseDouble(sB);
            if (y==0) {
                JOptionPane.showMessageDialog(null, "Divide by 0");
                sC = null;
            }
            else {
                z=x/y;
                sC = df.format(z);
            }
        }
        catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Input values must be numbers!");
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return sC;
    }

}
