package org.vendas.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vendas.br.model.Usuario;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByLogin(String login);
}
