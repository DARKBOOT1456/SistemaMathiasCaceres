/*
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
        Criteria criteria = session.createCriteria(MscServicos.class);
        criteria.add(Restrictions.eq("idmsc_servicos", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }
public Object listNomeServico(String NomeServico) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscServicos.class);
        criteria.add(Restrictions.like("msc_nome_servico", "%" + NomeServico + "%"));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listValor(double valor) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscServicos.class);
        criteria.add(Restrictions.ge("msc_valor", "%" + valor + "%"));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    public Object listNomeServicoValor(String NomeServico, double valor) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscServicos.class);
        criteria.add(Restrictions.like("msc_nome_servico", "%" + NomeServico + "%"));
        criteria.add(Restrictions.ge("msc_valor", "%" + valor + "%"));
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
     
}
