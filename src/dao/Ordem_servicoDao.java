/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bean.Ordem_servicoBean;
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
public class Ordem_servicoDao extends DaoAbstract{ 
    Connection cnt;
    public Ordem_servicoDao(){
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
        Ordem_servicoBean ordem_servico = (Ordem_servicoBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("insert into msc_ordensservico values(?,?,?,?,?,?,?,?)");
            pst.setInt(1, ordem_servico.getMsc_idOrdensServico());
            java.sql.Date sqlDate = new java.sql.Date(ordem_servico.getMsc_data_inicio().getTime());
        pst.setDate(2, sqlDate);
            pst.setString(3, ordem_servico.getMsc_status());
            pst.setString(4, ordem_servico.getMsc_valorTotal());
            pst.setString(5, ordem_servico.getMsc_Tecnico_responsavel());
            pst.setInt(6, ordem_servico.getMsc_fkCliente());
            pst.setInt(7, ordem_servico.getMsc_fkUsuarios());
            pst.setInt(8, ordem_servico.getMsc_fkServico()); 
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Ordem_servicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void update(Object object) {
       Ordem_servicoBean ordem_servico = (Ordem_servicoBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("update msc_ordensservico set msc_data_inicio=?, msc_status=?, msc_tecnico_responsavel=?, msc_valorTotal=?, fkmsc_Cliente=?, fkmsc_usuarios=?, fkmsc_servico=? where idmsc_ordensServico=?");
            pst.setInt(8, ordem_servico.getMsc_idOrdensServico());
            java.sql.Date sqlDate = new java.sql.Date(ordem_servico.getMsc_data_inicio().getTime());
        pst.setDate(1, sqlDate);
            pst.setString(2, ordem_servico.getMsc_status());
            pst.setString(3, ordem_servico.getMsc_Tecnico_responsavel());
             pst.setString(4, ordem_servico.getMsc_valorTotal());
            pst.setInt(5, ordem_servico.getMsc_fkCliente());
            pst.setInt(6, ordem_servico.getMsc_fkUsuarios());
            pst.setInt(7, ordem_servico.getMsc_fkServico());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Ordem_servicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Object object) {
        Ordem_servicoBean ordem_servico = (Ordem_servicoBean) object;
       
        try {
            PreparedStatement pst = cnt.prepareStatement("delete from msc_ordensservico where idmsc_ordensServico=?");
            pst.setInt(1, ordem_servico.getMsc_idOrdensServico());
            
            pst.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(Ordem_servicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Object list(int id) {
       
        try {
            PreparedStatement pst = cnt.prepareStatement("select * from msc_ordensservico where idmsc_ordensServico=?");
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()==true){
                Ordem_servicoBean ordem_servico = new Ordem_servicoBean();
                ordem_servico.setMsc_idOrdensServico(rs.getInt("idmsc_ordensServico"));
                ordem_servico.setMsc_data_inicio(rs.getDate("msc_data_inicio"));
                ordem_servico.setMsc_status(rs.getString("msc_status"));
                ordem_servico.setMsc_Tecnico_responsavel(rs.getString("msc_tecnico_responsavel"));
                 ordem_servico.setMsc_valorTotal(rs.getString("msc_valorTotal"));
                ordem_servico.setMsc_fkCliente(rs.getInt("fkmsc_Cliente"));
                ordem_servico.setMsc_fkUsuarios(rs.getInt("fkmsc_usuarios"));
                ordem_servico.setMsc_fkServico(rs.getInt("fkmsc_servico"));

                return ordem_servico;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ordem_servicoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Object listaAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) throws ParseException {
        Ordem_servicoBean ordem_servico = new Ordem_servicoBean();
        ordem_servico.setMsc_idOrdensServico(127);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date data = sdf.parse("15/03/1990");
        ordem_servico.setMsc_data_inicio(data);
        ordem_servico.setMsc_status("Em andamento");
        ordem_servico.setMsc_Tecnico_responsavel("mathias");
         ordem_servico.setMsc_valorTotal("345.99");
    ordem_servico.setMsc_fkCliente(1); 
    ordem_servico.setMsc_fkUsuarios(7);
    ordem_servico.setMsc_fkServico(22);
    
        Ordem_servicoDao ordem_servicoDao = new Ordem_servicoDao();
        ordem_servicoDao.insert(ordem_servico);
        System.out.println("Executado com sucesso.");
    }

   
  
}