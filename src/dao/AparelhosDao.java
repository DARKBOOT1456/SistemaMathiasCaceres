/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bean.AparelhosBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathi
 */
public class AparelhosDao extends DaoAbstract{
    
    public AparelhosDao(){
           
    }
    @Override
    public void insert(Object object) {
        AparelhosBean aparelhos = (AparelhosBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("insert into msc_aparelhos values(?,?,?,?,?,?,?,?)");
            pst.setInt(1, aparelhos.getMsc_idAparelhos());
            pst.setString(2, aparelhos.getMsc_cor());
            pst.setString(3, aparelhos.getMsc_marca());
            pst.setString(4, aparelhos.getMsc_modelo());
            pst.setString(5, aparelhos.getMsc_numero_de_serie());
             if (aparelhos.getMsc_data_entrada() != null) {
    pst.setDate(6, new java.sql.Date(aparelhos.getMsc_data_entrada().getTime()));
} else {
    pst.setNull(6, java.sql.Types.DATE);
}
            pst.setString(7, aparelhos.getMsc_chipRetirado());
            pst.setString(8, aparelhos.getMsc_tipodeEquipamento()); 
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AparelhosDao.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void update(Object object) {
       AparelhosBean aparelhos = (AparelhosBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("update msc_aparelhos set msc_cor=?, msc_marca=?, msc_modelo=?, msc_numero_de_serie=?, msc_data_entrada=?, msc_chipRetirado=?, msc_tipodeEquipamento=? where idmsc_aparelhos=?");
            pst.setInt(8, aparelhos.getMsc_idAparelhos());
            pst.setString(1, aparelhos.getMsc_cor());
            pst.setString(2, aparelhos.getMsc_marca());
            pst.setString(3, aparelhos.getMsc_modelo());
            pst.setString(4, aparelhos.getMsc_numero_de_serie());
            if (aparelhos.getMsc_data_entrada() != null) {
    pst.setDate(5, new java.sql.Date(aparelhos.getMsc_data_entrada().getTime()));
} else {
    pst.setNull(5, java.sql.Types.DATE);
}
            pst.setString(6, aparelhos.getMsc_chipRetirado());
            pst.setString(7, aparelhos.getMsc_tipodeEquipamento());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AparelhosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Object object) {
        AparelhosBean aparelhos = (AparelhosBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("delete from msc_aparelhos where idmsc_aparelhos=?");
            pst.setInt(1, aparelhos.getMsc_idAparelhos());
            
            pst.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(AparelhosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Object list(int id) {
       
        try {
            PreparedStatement pst = cnt.prepareStatement("select * from msc_aparelhos where idmsc_aparelhos=?");
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()==true){
                AparelhosBean aparelhos = new AparelhosBean();
                
                aparelhos.setMsc_idAparelhos(rs.getInt("idmsc_aparelhos"));
                aparelhos.setMsc_cor(rs.getString("msc_cor"));
                aparelhos.setMsc_marca(rs.getString("msc_marca"));
                aparelhos.setMsc_modelo(rs.getString("msc_modelo"));
                aparelhos.setMsc_numero_de_serie(rs.getString("msc_numero_de_serie"));
                aparelhos.setMsc_data_entrada(rs.getDate("msc_data_entrada"));
                aparelhos.setMsc_chipRetirado(rs.getString("msc_chipRetirado"));
                aparelhos.setMsc_tipodeEquipamento(rs.getString("msc_tipodeEquipamento"));

                return aparelhos;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AparelhosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Object listaAll() {
       List lista;
    try {
            PreparedStatement pst = cnt.prepareStatement("SELECT * FROM msc_aparelhos");
            ResultSet rs = pst.executeQuery();
            lista = new ArrayList();
            while(rs.next()==true){
                AparelhosBean aparelhos = new AparelhosBean();
                
                aparelhos.setMsc_idAparelhos(rs.getInt("idmsc_aparelhos"));
                aparelhos.setMsc_cor(rs.getString("msc_cor"));
                aparelhos.setMsc_marca(rs.getString("msc_marca"));
                aparelhos.setMsc_modelo(rs.getString("msc_modelo"));
                aparelhos.setMsc_numero_de_serie(rs.getString("msc_numero_de_serie"));
                 String dataStr = rs.getString("msc_data_entrada");
            if (dataStr == null || dataStr.equals("0000-00-00")) {
                aparelhos.setMsc_data_entrada(null);
            } else {
                aparelhos.setMsc_data_entrada(rs.getDate("msc_data_entrada"));
            }
                aparelhos.setMsc_chipRetirado(rs.getString("msc_chipRetirado"));
                aparelhos.setMsc_tipodeEquipamento(rs.getString("msc_tipodeEquipamento"));
                lista.add(aparelhos);            
                
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(AparelhosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;   
    }
    
    public static void main(String[] args) throws ParseException {
        AparelhosBean aparelhos = new AparelhosBean();
        aparelhos.setMsc_idAparelhos(188);
        aparelhos.setMsc_cor("branco");
        aparelhos.setMsc_marca("Sansung");
        aparelhos.setMsc_modelo("j5 Prime");
        aparelhos.setMsc_numero_de_serie("13"); 
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date data = sdf.parse("15/03/1990");
    aparelhos.setMsc_data_entrada(data);
    aparelhos.setMsc_tipodeEquipamento("Celular");
    aparelhos.setMsc_chipRetirado("retirado");
        AparelhosDao aparelhosDao = new AparelhosDao();
        aparelhosDao.insert(aparelhos);
        System.out.println("Executado com sucesso.");
    }

   
   
}
