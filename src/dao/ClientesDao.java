/*
<<<<<<< HEAD
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bean.ClientesBean;
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
public class ClientesDao extends DaoAbstract{
    
    public ClientesDao(){
            
    }
    @Override
    public void insert(Object object) {
        ClientesBean clientes = (ClientesBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("insert into msc_clientes values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, clientes.getMsc_idclientes());
            pst.setString(2, clientes.getMsc_nome());
            pst.setString(3, clientes.getMsc_cpf());
            pst.setString(4, clientes.getMsc_rg());
             if (clientes.getMsc_data_nascimento() != null) {
    pst.setDate(5, new java.sql.Date(clientes.getMsc_data_nascimento().getTime()));
} else {
    pst.setNull(5, java.sql.Types.DATE);
}    
            pst.setString(6, clientes.getMsc_telefone());
            pst.setString(7, clientes.getMsc_telefone_secundario());
            pst.setString(8, clientes.getMsc_email());
             pst.setString(9, clientes.getMsc_endereco()); 
              pst.setString(10, clientes.getMsc_bairro()); 
               pst.setString(11, clientes.getMsc_cidade()); 
                pst.setString(12, clientes.getMsc_estado()); 
                 pst.setString(13, clientes.getMsc_cep()); 
                 if (clientes.getMsc_data_cadastro() != null) {
    pst.setDate(14, new java.sql.Date(clientes.getMsc_data_cadastro().getTime()));
} else {
    pst.setNull(14, java.sql.Types.DATE);
}    
                   pst.setString(15, clientes.getMsc_deficiencia()); 
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDao.class.getName()).log(Level.SEVERE, null, ex);
        }   
=======
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.MscClientes;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author @mathias
 */
public class ClientesDao extends AbstractDao {

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
       ClientesBean clientes = (ClientesBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("update msc_clientes set msc_nome=?,msc_cpf=?,msc_rg=?,msc_data_nascimento=?,msc_telefone=?,msc_telefone_secundario=?,msc_email=?,msc_endereco=?,msc_bairro=?,msc_cidade=?,msc_estado=?,msc_cep=?,msc_data_cadastro=?,msc_deficiencia=? where idmsc_clientes=?");
            pst.setInt(15, clientes.getMsc_idclientes());
            pst.setString(1, clientes.getMsc_nome());
            pst.setString(2, clientes.getMsc_cpf());
            pst.setString(3, clientes.getMsc_rg());
            if (clientes.getMsc_data_nascimento() != null) {
    pst.setDate(4, new java.sql.Date(clientes.getMsc_data_nascimento().getTime()));
} else {
    pst.setNull(4, java.sql.Types.DATE);
}
            pst.setString(5, clientes.getMsc_telefone());
            pst.setString(6, clientes.getMsc_telefone_secundario());
             pst.setString(7, clientes.getMsc_email());
            pst.setString(8, clientes.getMsc_endereco());
             pst.setString(9, clientes.getMsc_bairro());
            pst.setString(10, clientes.getMsc_cidade());
             pst.setString(11, clientes.getMsc_estado());
            pst.setString(12, clientes.getMsc_cep());
            if (clientes.getMsc_data_cadastro() != null) {
    pst.setDate(13, new java.sql.Date(clientes.getMsc_data_cadastro().getTime()));
} else {
    pst.setNull(13, java.sql.Types.DATE);
}
            pst.setString(14, clientes.getMsc_deficiencia());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDao.class.getName()).log(Level.SEVERE, null, ex);
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
        ClientesBean clientes = (ClientesBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("delete from msc_clientes where idmsc_clientes=?");
            pst.setInt(1, clientes.getMsc_idclientes());
            
            pst.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Object list(int id) {
       
        try {
            PreparedStatement pst = cnt.prepareStatement("select * from msc_clientes where idmsc_clientes=?");
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()==true){
                ClientesBean clientes = new ClientesBean();
                
                clientes.setMsc_idclientes(rs.getInt("idmsc_clientes"));
                clientes.setMsc_nome(rs.getString("msc_nome"));
                clientes.setMsc_cpf(rs.getString("msc_cpf"));
                clientes.setMsc_rg(rs.getString("msc_rg"));
                clientes.setMsc_data_nascimento(rs.getDate("msc_data_nascimento"));
                clientes.setMsc_telefone(rs.getString("msc_telefone"));
                clientes.setMsc_telefone_secundario(rs.getString("msc_telefone_secundario"));
                clientes.setMsc_email(rs.getString("msc_email"));
                clientes.setMsc_endereco(rs.getString("msc_endereco"));
                clientes.setMsc_bairro(rs.getString("msc_bairro"));
                clientes.setMsc_cidade(rs.getString("msc_cidade"));
                clientes.setMsc_estado(rs.getString("msc_estado"));
                clientes.setMsc_cep(rs.getString("msc_cep"));
                clientes.setMsc_data_cadastro(rs.getDate("msc_data_cadastro"));
                clientes.setMsc_deficiencia(rs.getString("msc_deficiencia"));
    
                return clientes;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Object listaAll() {
         List lista;
    try {
            PreparedStatement pst = cnt.prepareStatement("SELECT * FROM msc_clientes");
            ResultSet rs = pst.executeQuery();
            lista = new ArrayList();
            while(rs.next()==true){
                
               ClientesBean clientes = new ClientesBean();
                
                clientes.setMsc_idclientes(rs.getInt("idmsc_clientes"));
                clientes.setMsc_nome(rs.getString("msc_nome"));
                clientes.setMsc_cpf(rs.getString("msc_cpf"));
                clientes.setMsc_rg(rs.getString("msc_rg"));
                clientes.setMsc_data_nascimento(rs.getDate("msc_data_nascimento"));
                clientes.setMsc_telefone(rs.getString("msc_telefone"));
                clientes.setMsc_telefone_secundario(rs.getString("msc_telefone_secundario"));
                clientes.setMsc_email(rs.getString("msc_email"));
                clientes.setMsc_endereco(rs.getString("msc_endereco"));
                clientes.setMsc_bairro(rs.getString("msc_bairro"));
                clientes.setMsc_cidade(rs.getString("msc_cidade"));
                clientes.setMsc_estado(rs.getString("msc_estado"));
                clientes.setMsc_cep(rs.getString("msc_cep"));
                clientes.setMsc_data_cadastro(rs.getDate("msc_data_cadastro"));
                clientes.setMsc_deficiencia(rs.getString("msc_deficiencia"));
                lista.add(clientes); 
            }
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;   
       }
    
    public static void main(String[] args) throws ParseException {
        ClientesBean clientes = new ClientesBean();
        clientes.setMsc_idclientes(99);
        clientes.setMsc_nome("mathias");
        clientes.setMsc_cpf("10918857104");
        clientes.setMsc_rg("73822344");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date data = sdf.parse("15/03/1990");
    clientes.setMsc_data_nascimento(data);     
    clientes.setMsc_telefone("67991827336");
    clientes.setMsc_telefone_secundario("67993618812");
    clientes.setMsc_email("mathias.caceres");
    clientes.setMsc_endereco("avenida");
    clientes.setMsc_bairro("centro");
    clientes.setMsc_cidade("aral");
    clientes.setMsc_estado("ms");
    clientes.setMsc_cep("79930000");
    clientes.setMsc_data_cadastro(data);
     clientes.setMsc_deficiencia("Autismo");
  
     
    
        ClientesDao clientesDao = new ClientesDao();
        clientesDao.insert(clientes);
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
        Criteria criteria = session.createCriteria(MscClientes.class);
        criteria.add(Restrictions.eq("idmsc_clientes", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    @Override
    public Object listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscClientes.class);
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;    }
    public static void main(String[]args){
        ClientesDao clientesDao = new ClientesDao();
        clientesDao.listAll();
    }
     
>>>>>>> c306d2f80dbe35c36662e202c193df1adabc7d2e
}
