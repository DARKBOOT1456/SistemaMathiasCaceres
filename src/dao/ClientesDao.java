/*
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
    }

    @Override
    public void update(Object object) {
        session.beginTransaction();
        session.flush();
        session.clear();
        session.update(object);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Object object) {
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
public Object listNome(String nome) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscClientes.class);
        criteria.add(Restrictions.like("mscNome", "%" + nome + "%"));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listCpf(String cpf) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscClientes.class);
        criteria.add(Restrictions.ge("mscCpf", "%" + cpf + "%"));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listNomeCpf(String nome, String cpf) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscClientes.class);
        criteria.add(Restrictions.like("mscNome", "%" + nome + "%"));
        criteria.add(Restrictions.ge("mscCpf", "%" + cpf + "%"));
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
     
}
