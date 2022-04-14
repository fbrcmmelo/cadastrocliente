package org.vendas.br.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.vendas.br.model.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteJdbc {

    private static final String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?) ";
    private static final String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ? ";
    private static final String DELETE = "DELETE FROM CLIENTE WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM CLIENTE";
    private JdbcTemplate jdbcTemplate;
    
    public ClienteJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Cliente> findAll() {
       return jdbcTemplate.query(SELECT_ALL, getRowMapper());
    }

    private RowMapper<Cliente> getRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Cliente(resultSet.getInt("id"),
                        resultSet.getString("nome"));
            }
        };
    }

    public void save (Cliente cliente) { jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()}); }

    public void update (Cliente cliente) { jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()}); }

    public void delete (Integer id) { jdbcTemplate.update(DELETE, new Object[]{id}); }

    public List<Cliente> findByNome (String nome) {
        return jdbcTemplate.query(SELECT_ALL.concat(" WHERE NOME LIKE ?"),
                new Object[]{"%" + nome + "%"}, getRowMapper());
    }
}
