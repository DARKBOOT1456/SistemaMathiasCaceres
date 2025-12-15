/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.MscOrdensServico;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author @mathias
 */
public class Ordem_servicoDao extends AbstractDao {

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
        Criteria criteria = session.createCriteria(MscOrdensServico.class);
        criteria.add(Restrictions.eq("idmsc_ordensServico", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }
public Object listTec(String tec) {
    session.beginTransaction();
    Criteria criteria = session.createCriteria(MscOrdensServico.class);
    criteria.add(Restrictions.like("mscTecnicoResponsavel", "%" + tec + "%"));
    List lista = criteria.list();
    session.getTransaction().commit();
    return lista;
}


    public Object listValor(double valor) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscOrdensServico.class);
        criteria.add(Restrictions.ge("mscValorTotal", valor));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listTecValor(String tec, double valor) {
    session.beginTransaction();
    Criteria criteria = session.createCriteria(MscOrdensServico.class); 
      criteria.add(Restrictions.like("mscTecnicoResponsavel", "%" + tec + "%"));
    criteria.add(Restrictions.ge("mscValorTotal", valor));
    List lista = criteria.list();
    session.getTransaction().commit();
    return lista;
}
    
    @Override
    public Object listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscOrdensServico.class);
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;    }
    public static void main(String[]args){
        Ordem_servicoDao ordem_servicoDao = new Ordem_servicoDao();
        ordem_servicoDao.listAll();
    }
     
}
