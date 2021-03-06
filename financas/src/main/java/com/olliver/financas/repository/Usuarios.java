package com.olliver.financas.repository;

import com.olliver.financas.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author olliver
 */
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    @Inject
    public Usuarios(EntityManager manager) {
        this.manager = manager;
    }

    public Usuario porId(Long id) {
        return manager.find(Usuario.class, id);
    }

    public Usuario porEmail(String email) {
        Query query = manager.createQuery("select u from Usuario u INNER JOIN FETCH u.licenca where u.email=:email", Usuario.class);
        query.setParameter("email", email);
        return (Usuario) query.getSingleResult();
    }

    public List<Usuario> todos() {
        return manager.createQuery("from Usuario", Usuario.class).getResultList();
    }

    public void adicionar(Usuario usuario) {
        manager.persist(usuario);
    }

    public Usuario guardar(Usuario usuario) {
        return manager.merge(usuario);
    }

    public void remover(Usuario usuario) {
        manager.remove(usuario);
    }
}
