/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author u10918857104
 */
public abstract class DaoAbstract  {
   
   Connection cnt;
   
    public DaoAbstract(){
         try {
            Class.forName("com.mysql.jdbc.Driver");
            String url, user, pass;
            //url = "jdbc:mysql://10.7.0.51:33062/db_mathias_caceres";
            //user = "mathias_caceres";
            //pass = "mathias_caceres";
            url = "jdbc:mysql://localhost:3306/vendas";
            user = "root";
            pass = "";
            
            cnt = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuariosDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
   
    public abstract void insert(Object object);
    public abstract void update(Object object);
    public abstract void delete(Object object);
    public abstract Object list(int id);
    public abstract Object listaAll();
   
   
}
