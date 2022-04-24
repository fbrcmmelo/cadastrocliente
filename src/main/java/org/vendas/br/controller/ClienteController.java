package org.vendas.br.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.vendas.br.dto.ClienteDTO;
import org.vendas.br.model.Cliente;
import org.vendas.br.service.Impl.ClienteServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

    /*Anotaçoes -- Quando usamos @controller só estamos dizendo ao spring que nossa classe será controladora de requisições
            mas não define o tipo dessas requisiçoes, então temos que tratar isso nas anotações para cada método,
            Exemplo abaixo:
            @RequestMapping(
                    method = RequestMethod.POST,
                    value = {"/api/metodo", "/cliente/metodo"},  ** podemos definir mais de uma url
                    consumes = {"appĺication/json", "application.xml"}, ** tipo do corpo da requisição mimeType's' que irá aceitar
                    produces = {"application/json", "application.xml"} ** tipo do corpo da requisição que será enviada como resposta
            )
            public String metodo () { return "metodo"}
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServiceImpl instanceOfClienteService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid ClienteDTO dto) {
        Cliente cliente = instanceOfClienteService.salvar(dto);
        return cliente;
    }

    @PutMapping("{id}")
    public Cliente atualizar(@PathVariable("id") Integer id,
                             @RequestBody @Valid ClienteDTO dto) {
        Cliente cliente = instanceOfClienteService.atualizar(id, dto);
        if (cliente != null) {
            return cliente;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void remover(@PathVariable Integer id) {
        instanceOfClienteService.remover(id);
    }

    @GetMapping("{id}")
    public Cliente buscarPorId(@PathVariable Integer id) {
        Optional<Cliente> cliente = instanceOfClienteService.buscarPorId(id);
        if (cliente.isPresent()) {
            return cliente.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public List<Cliente> buscarTodos(Cliente filtroCliente) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtroCliente, matcher);
        List<Cliente> listaClientes = instanceOfClienteService.buscarTodosComFiltro(example);
        return listaClientes;
    }
}
