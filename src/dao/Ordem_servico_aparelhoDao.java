/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bean.Ordem_servico_AparelhoBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathi
 */
public class Ordem_servico_aparelhoDao extends DaoAbstract{
    Connection cnt;
    public Ordem_servico_aparelhoDao(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url, user, pass;
            url = "jdbc:mysql://localhost:3306/vendas";
            user = "root";
            pass = "";
            //url = "jdbc:mysql://10.7.0.51:33062/db_mathias_caceres";
            //user = "mathias_caceres";
            //pass = "mathias_caceres";
            
            cnt = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ordem_servicoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Ordem_servicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    @Override
    public void insert(Object object) {
        Ordem_servico_AparelhoBean ordem_servico_Aparelho = (Ordem_servico_AparelhoBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("insert into msc_ordem_servico_aparelho values(?,?,?,?,?,?)");
            pst.setInt(1, ordem_servico_Aparelho.getMsc_idOsAparelho());
            pst.setInt(2, ordem_servico_Aparelho.getMsc_quantidade());
            pst.setDouble(3, ordem_servico_Aparelho.getMsc_valor_unitario());
            pst.setInt(4, ordem_servico_Aparelho.getMsc_fkAparelho());
            pst.setString(5, ordem_servico_Aparelho.getMsc_observacoes());
            pst.setInt(6, ordem_servico_Aparelho.getMsc_fkOs());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Ordem_servico_aparelhoDao.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void update(Object object) {
       Ordem_servico_AparelhoBean ordem_servico_Aparelho = (Ordem_servico_AparelhoBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("update msc_ordem_servico_aparelho set msc_quantidade=?, msc_valor_unitario=?, msc_fkAparelho=?, msc_observacoes=?, msc_fkOs=?  where msc_idOs_Aparelho=?");
            pst.setInt(6, ordem_servico_Aparelho.getMsc_idOsAparelho());
            pst.setInt(1, ordem_servico_Aparelho.getMsc_quantidade());
            pst.setDouble(2, ordem_servico_Aparelho.getMsc_valor_unitario());
             pst.setInt(3, ordem_servico_Aparelho.getMsc_fkAparelho());
            pst.setString(4, ordem_servico_Aparelho.getMsc_observacoes());
            pst.setInt(5, ordem_servico_Aparelho.getMsc_fkOs());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Ordem_servico_aparelhoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Object object) {
        Ordem_servico_AparelhoBean ordem_servico_Aparelho = (Ordem_servico_AparelhoBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("delete from msc_ordem_servico_aparelho where msc_idOs_Aparelho=?");
            pst.setInt(1, ordem_servico_Aparelho.getMsc_idOsAparelho());
            
            pst.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(Ordem_servico_aparelhoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Object list(int id) {
       
        try {
            PreparedStatement pst = cnt.prepareStatement("select * from msc_ordem_servico_aparelho where msc_idOs_Aparelho=?");
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()==true){
                Ordem_servico_AparelhoBean ordem_servico_Aparelho = new Ordem_servico_AparelhoBean();
                ordem_servico_Aparelho.setMsc_idOsAparelho(rs.getInt("msc_idOs_Aparelho"));
                ordem_servico_Aparelho.setMsc_quantidade(rs.getInt("msc_quantidade"));
                ordem_servico_Aparelho.setMsc_valor_unitario(rs.getDouble("msc_valor_unitario"));
                ordem_servico_Aparelho.setMsc_fkAparelho(rs.getInt("msc_fkAparelho"));
                 ordem_servico_Aparelho.setMsc_observacoes(rs.getString("msc_observacoes"));
                ordem_servico_Aparelho.setMsc_fkOs(rs.getInt("msc_fkOs"));

                return ordem_servico_Aparelho;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ordem_servico_AparelhoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Object listaAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) throws ParseException {
        Ordem_servico_AparelhoBean ordem_servico_Aparelho = new Ordem_servico_AparelhoBean();
        ordem_servico_Aparelho.setMsc_idOsAparelho(402);
        ordem_servico_Aparelho.setMsc_quantidade(874);
        ordem_servico_Aparelho.setMsc_valor_unitario(789.99);
        ordem_servico_Aparelho.setMsc_fkAparelho(2);
         ordem_servico_Aparelho.setMsc_observacoes("sem observaçoes");
    ordem_servico_Aparelho.setMsc_fkOs(45); 
    
        Ordem_servico_aparelhoDao ordem_servico_aparelhoDao = new Ordem_servico_aparelhoDao();
        ordem_servico_aparelhoDao.insert(ordem_servico_aparelhoDao);
        System.out.println("Executado com sucesso.");
    }

   
  
}
