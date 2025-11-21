/*
<<<<<<< HEAD
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bean.ServicosBean;
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
public class ServicosDao extends DaoAbstract{
    Connection cnt;
    public ServicosDao(){
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
            Logger.getLogger(UsuariosDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    @Override
    public void insert(Object object) {
        ServicosBean servicos = (ServicosBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("insert into msc_servicos values(?,?,?,?,?,?,?)");
            pst.setInt(1, servicos.getMsc_idServicos());
            pst.setString(2, servicos.getMsc_nome_servico());
            pst.setString(3, servicos.getMsc_descricao());
            pst.setDouble(4, servicos.getMsc_valor());
            pst.setString(5, servicos.getMsc_tempo_estimado());
            pst.setString(6, servicos.getMsc_categoria());     
            java.sql.Date sqlDate = new java.sql.Date(servicos.getMsc_data_cadastro().getTime());
        pst.setDate(7, sqlDate); 

            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosDao.class.getName()).log(Level.SEVERE, null, ex);
        }   
=======
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import bean.MscServicos;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author @mathias
 */
public class ServicosDao extends AbstractDao {

    @Override
    public void insert(Object object) {
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }

    @Override
    public void update(Object object) {
<<<<<<< HEAD
       ServicosBean servicos = (ServicosBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("update msc_servicos set msc_nome_servico=?, msc_descricao=?, msc_valor=?, msc_tempo_estimado=?, msc_categoria=?, msc_data_cadastro=? where idmsc_servicos=?");
            pst.setInt(7, servicos.getMsc_idServicos());
            pst.setString(1, servicos.getMsc_nome_servico());
            pst.setString(2, servicos.getMsc_descricao());
            pst.setDouble(3, servicos.getMsc_valor());
            pst.setString(4, servicos.getMsc_tempo_estimado());
            pst.setString(5, servicos.getMsc_categoria());
            if (servicos.getMsc_data_cadastro() != null) {
    pst.setDate(6, new java.sql.Date(servicos.getMsc_data_cadastro().getTime()));
} else {
    pst.setNull(6, java.sql.Types.DATE);
}
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
=======
        session.beginTransaction();
        session.flush();
        session.clear();
        session.update(object);
        session.getTransaction().commit();
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
    }

    @Override
    public void delete(Object object) {
<<<<<<< HEAD
        ServicosBean servicos = (ServicosBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("delete from msc_servicos where idmsc_servicos=?");
            pst.setInt(1, servicos.getMsc_idServicos());
            
            pst.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(ServicosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Object list(int id) {
       
        try {
            PreparedStatement pst = cnt.prepareStatement("select * from msc_servicos where idmsc_servicos=?");
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()==true){
                ServicosBean servicos = new ServicosBean();
                
                servicos.setMsc_idServicos(rs.getInt("idmsc_servicos"));
                servicos.setMsc_nome_servico(rs.getString("msc_nome_servico"));
                servicos.setMsc_descricao(rs.getString("msc_descricao"));
                servicos.setMsc_valor(rs.getDouble("msc_valor"));
                servicos.setMsc_tempo_estimado(rs.getString("msc_tempo_estimado"));
                servicos.setMsc_categoria(rs.getString("msc_categoria"));
                servicos.setMsc_data_cadastro(rs.getDate("msc_data_cadastro"));
             
                return servicos;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Object listaAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) throws ParseException {
        ServicosBean servicos = new ServicosBean();
        servicos.setMsc_idServicos(202);
        servicos.setMsc_nome_servico("Troca de tela");
        servicos.setMsc_descricao("Troca de tela mais a pelicula");
        servicos.setMsc_valor(250.00);
    servicos.setMsc_tempo_estimado("uma semana");
        servicos.setMsc_categoria("senha342"); 
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date data = sdf.parse("15/03/1990");
    servicos.setMsc_data_cadastro(data);
        ServicosDao servicosDao = new ServicosDao();
        servicosDao.insert(servicos);
        System.out.println("Executado com sucesso.");
    }

   
   
=======
        session.beginTransaction();
        session.flush();
        session.clear();
        session.delete(object);
        session.getTransaction().commit();
    }

    @Override
    public Object list(int codigo) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscServicos.class);
        criteria.add(Restrictions.eq("idmsc_servicos", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    @Override
    public Object listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscServicos.class);
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;    }
    public static void main(String[]args){
       ServicosDao servicosDao = new ServicosDao();
        servicosDao.listAll();
    }
     
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
}
