/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bean.UsuariosBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author u10918857104
 */
public class UsuariosDao extends DaoAbstract{
    Connection cnt;
    public UsuariosDao(){
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
        UsuariosBean usuarios = (UsuariosBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("insert into msc_usuarios values(?,?,?,?,?,?,?,?)");
            pst.setInt(1, usuarios.getMsc_idusuarios());
            pst.setString(2, usuarios.getMsc_nome());//nome
            pst.setString(3, usuarios.getMsc_apelido());//apelido
            pst.setString(4, usuarios.getMsc_cpf());//cpf
             java.sql.Date sqlDate = new java.sql.Date(usuarios.getMsc_dataNascimento().getTime());
        pst.setDate(5, sqlDate); 

            pst.setString(6, usuarios.getMsc_senha());//senha
           pst.setString(7, usuarios.getMsc_ativo());//ativO
            pst.setString(8, usuarios.getMsc_nivel());//nivel
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDao.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void update(Object object) {
       UsuariosBean usuarios = (UsuariosBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("update msc_usuarios set msc_nome=?, msc_apelido=?, msc_cpf=?, msc_dataNascimento=?, msc_senha=?, msc_nivel=?,msc_ativo=? where idmsc_usuarios=?");
            pst.setInt(8, usuarios.getMsc_idusuarios());
            pst.setString(1, usuarios.getMsc_nome());//nome
            pst.setString(2, usuarios.getMsc_apelido());//apelido
            pst.setString(3, usuarios.getMsc_cpf());//cpf
           if (usuarios.getMsc_dataNascimento() != null) {
    pst.setDate(4, new java.sql.Date(usuarios.getMsc_dataNascimento().getTime()));
} else {
    pst.setNull(4, java.sql.Types.DATE);
}
            pst.setString(5, usuarios.getMsc_senha());//senha
            pst.setString(6, usuarios.getMsc_nivel());//nivel
            pst.setString(7, usuarios.getMsc_ativo());//ativo
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Object object) {
        UsuariosBean usuarios = (UsuariosBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("delete from msc_usuarios where idmsc_usuarios=?");
            pst.setInt(1, usuarios.getMsc_idusuarios());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    @Override
    public Object list(int id) {
       
        try {
            PreparedStatement pst = cnt.prepareStatement("select * from msc_usuarios where idmsc_usuarios=?");
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()==true){
                UsuariosBean usuarios = new UsuariosBean();
                
                usuarios.setMsc_idusuarios(rs.getInt("idmsc_usuarios"));
                usuarios.setMsc_nome(rs.getString("msc_nome"));
                usuarios.setMsc_apelido(rs.getString("msc_apelido"));
                usuarios.setMsc_cpf(rs.getString("msc_cpf"));
                usuarios.setMsc_dataNascimento(rs.getDate("msc_dataNascimento"));
                usuarios.setMsc_senha(rs.getString("msc_senha"));
                usuarios.setMsc_nivel(rs.getString("msc_nivel"));
                usuarios.setMsc_ativo(rs.getString("msc_ativo"));
             
                return usuarios;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Object listaAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) throws ParseException {
        UsuariosBean usuarios = new UsuariosBean();
        usuarios.setMsc_idusuarios(7);
        usuarios.setMsc_nome("mathias");
        usuarios.setMsc_apelido("apelido mpv");
        usuarios.setMsc_cpf("12312399287");
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date data = sdf.parse("15/03/1990");
    usuarios.setMsc_dataNascimento(data);
        usuarios.setMsc_senha("senha342");
        usuarios.setMsc_nivel("");
        usuarios.setMsc_ativo("S");
        
        UsuariosDao usuariosDao = new UsuariosDao();
        usuariosDao.insert(usuarios);
        System.out.println("Executado com sucesso.");
    }

   
   
}