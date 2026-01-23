/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.MscOrdensServico;
import java.util.Date;
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
     public List<MscOrdensServico> listarPorPeriodo(Date dataInicio, Date dataFim) {
    session.beginTransaction();
    try {
        Criteria criteria = session.createCriteria(MscOrdensServico.class);
        
        // Adiciona as restrições de data
        criteria.add(Restrictions.ge("mscDataInicio", dataInicio)); // maior ou igual à data início
        criteria.add(Restrictions.le("mscDataInicio", dataFim));    // menor ou igual à data fim
        
        // Ordena por data
        criteria.addOrder(org.hibernate.criterion.Order.asc("mscDataInicio"));
        
        List<MscOrdensServico> lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    } catch (Exception e) {
        if (session.getTransaction() != null) {
            session.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    }
}
     public List<MscOrdensServico> listarPorPeriodoCompleto(Date dataInicio, Date dataFim) {
    session.beginTransaction();
    try {
        Criteria criteria = session.createCriteria(MscOrdensServico.class);
        
        // Cria uma disjunção (OR) para verificar se a ordem está dentro do período
        // Usando mscDataInicio OU mscDataFim (se existir)
        org.hibernate.criterion.Disjunction disjunction = Restrictions.disjunction();
        
        // Se a data início da ordem estiver dentro do período
        disjunction.add(Restrictions.between("mscDataInicio", dataInicio, dataFim));
        
        // Se você tiver data de término, pode adicionar também:
        // disjunction.add(Restrictions.between("mscDataFim", dataInicio, dataFim));
        
        criteria.add(disjunction);
        criteria.addOrder(org.hibernate.criterion.Order.asc("mscDataInicio"));
        
        List<MscOrdensServico> lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    } catch (Exception e) {
        if (session.getTransaction() != null) {
            session.getTransaction().rollback();
        }
        e.printStackTrace();
        return null;
    }
}
}
