package org.vendas.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vendas.br.model.Cliente;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    //Consulta sql
    //@Query(value = "Select * from cliente c where c.nome like '%:nome%' ", nativeQuery=true)

    //Consulta Jpql
    //@Query(value = "Select c from Cliente c where c.nome like %:nome%")

    //@Modifying usado para abrir transacoes de delete, insert e update
    /*QueryMethods
    List<Cliente> findByNomeLike, findByNomeOrId, findOneByNome, deleteByNome, Sempre colocar o nome do atributo do Objeto
    exemplo findBy 'Nome' findBy 'Id' findBy 'Cpf' igualmente os atributos que voce tiver em sua Classe.*/

    List<Cliente> findByNomeLike(String nome);

    @Query(value = "Select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedido(Integer id);

}
