/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author u06425349182
 */
public abstract class AbstractDao {
    public Session session;
    
    public AbstractDao() {
    SessionFactory sesionFactory = HibernateUtil.getSessionFactory();
    session = sesionFactory.openSession();
    }
    
    
    
    public abstract void insert (Object object);
    public abstract void update (Object object);
    public abstract void delete (Object object);
    public abstract Object list (int codigo);
    public abstract Object listAll ();
}
