/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.MscOrdensServico;
import dao.Ordem_servicoDao;
import bean.MscOrdemServicoAparelho;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;



/**
 *
 * @author mathias
 */
public class OrdemServicoAparelhoDao extends AbstractDao{

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
public void deleteAparelhos(MscOrdensServico mscOrdensServico) {
        List lista = (List) listAparelhos(mscOrdensServico);
        session.beginTransaction();
        for (int i = 0; i < lista.size(); i++) {
            MscOrdemServicoAparelho mscOrdemServicoAparelho = (MscOrdemServicoAparelho) lista.get(i);
            session.flush();
            session.clear();
            session.delete(mscOrdemServicoAparelho);
        }
        session.getTransaction().commit();
    }
    @Override
    public Object list(int codigo) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscOrdemServicoAparelho.class);
        criteria.add(Restrictions.eq("msc_idOs_Aparelho", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();        
        return lista;
    }

      public Object listAparelhos(MscOrdensServico mscOrdensServico) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscOrdemServicoAparelho.class);
        criteria.add(Restrictions.eq("mscOrdensServico", mscOrdensServico));
        List lista = criteria.list();
        session.getTransaction().commit();        
        return lista;
    }
    
    
    @Override
    public Object listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscOrdemServicoAparelho.class);
        List lista = criteria.list();
        session.getTransaction().commit();        
        return lista;    
    }

    public static void main(String[] args) {
       OrdemServicoAparelhoDao ordemServicoAparelhoDao = new OrdemServicoAparelhoDao();
        ordemServicoAparelhoDao.listAll();
    }
}
