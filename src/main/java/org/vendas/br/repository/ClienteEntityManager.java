package org.vendas.br.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.vendas.br.model.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

@Repository @Transactional
public class ClienteEntityManager {

    private final EntityManager eManager;

    public ClienteEntityManager(EntityManager eManager) {
        this.eManager = eManager;
    }

    public void save (Cliente cliente) {
        eManager.persist(cliente);
    }

    public void update (Cliente cliente) {
        eManager.merge(cliente);
    }

    public void delete (Integer id) {
        Cliente cliente = eManager.find(Cliente.class, id);
        eManager.remove(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> findAll () {
        return eManager.createQuery("from Cliente", Cliente.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> findByNome (String nome) {
        String jpql = "select c from Cliente c where c.nome like :nome";
        return eManager.createQuery(jpql, Cliente.class)
                .setParameter("nome", "%"+nome+"%").getResultList();
    }
}
