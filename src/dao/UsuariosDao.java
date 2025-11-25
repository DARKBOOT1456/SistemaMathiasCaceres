package dao;

import bean.MscUsuarios;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class UsuariosDao extends AbstractDao {

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
        Criteria criteria = session.createCriteria(MscUsuarios.class);
        criteria.add(Restrictions.eq("idmscUsuarios", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    @Override
    public Object listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscUsuarios.class);
        List lista = criteria.list();
        session.getTransaction().commit();
        return lista;
    }

    // MÉTODO LOGIN AJUSTADO - APENAS ISSO MUDEI
    public MscUsuarios login(String apelido, String senha) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MscUsuarios.class);
        
        // Agora usando mscApelido em vez de mscNome
        criteria.add(Restrictions.eq("mscApelido", apelido));
        criteria.add(Restrictions.eq("mscSenha", senha));

        List<MscUsuarios> lista = criteria.list();
        session.getTransaction().commit();

        if (!lista.isEmpty()) {
            return lista.get(0); 
        } else {
            return null; 
        }
    }

    public static void main(String[] args) {
        UsuariosDao usuariosDao = new UsuariosDao();
        
        // Agora usa apelido e senha
        MscUsuarios usuario = usuariosDao.login("apelidoDoUsuario", "senhaDoUsuario");

        if (usuario != null) {
            System.out.println("Login bem-sucedido! Usuário: " + usuario.getMscNome());
        } else {
            System.out.println("Apelido ou senha inválidos.");
        }
    }
}