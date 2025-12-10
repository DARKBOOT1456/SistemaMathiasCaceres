/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author mathias
 */
public class Util {
    
    public static void habilitar(boolean valor, JComponent... componentes) {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].setEnabled(valor);
        }
    }
    
    public static void limpar(JComponent... componentes) {
        for (int i = 0; i < componentes.length; i++) {
            if (componentes[i] instanceof JTextField) {
                ((JTextField) componentes[i]).setText("");
                //instanceof
            }
        }
    }
    
    public static void mensagem(String cad) {
        JOptionPane.showMessageDialog(null, cad);        
    }
    
    public static boolean perguntar(String cad){
        return JOptionPane.showConfirmDialog(null, cad)== JOptionPane.YES_OPTION;
       
    }
    
    public static int strToInt(String num) {
        return Integer.valueOf(num);
    }

    public static String intToStr(int num) {
        return String.valueOf(num);
    }    
    
    public static double strToDouble(String num) {
        return Double.parseDouble(num);
    }

    public static String doubleToStr(double num) {
        return String.valueOf(num);
    }
    
    public static Date strToDate(String data) {
    SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy"); 
    fm.setLenient(false);
    try {
        return fm.parse(data);
    } catch (ParseException ex) {
        Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}

    public static String dateToStr(Date data) {
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/YYYY");
        return fm.format(data);
    }
    
}
