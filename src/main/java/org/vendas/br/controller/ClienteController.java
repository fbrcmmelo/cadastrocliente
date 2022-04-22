package org.vendas.br.controller;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.vendas.br.model.Cliente;
import org.vendas.br.repository.ClienteRepository;

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

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteRepository instanceOfCliente;

    public ClienteController(ClienteRepository instanceOfCliente) {
        if (this.instanceOfCliente == null) {
            this.instanceOfCliente = instanceOfCliente;
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente saveCliente (@RequestBody @Valid Cliente clienteParameter) {
        Cliente cliente = instanceOfCliente.save(clienteParameter);
        return cliente;
    }

    @PutMapping("{id}")
    public Cliente updateCliente (@PathVariable("id") Integer idCliente,
                                  @RequestBody @Valid Cliente clienteParameter) {
        Optional<Cliente> cliente = instanceOfCliente.findById(idCliente);
        if (cliente.isPresent()) {
            clienteParameter.setId(cliente.get().getId());
            instanceOfCliente.save(clienteParameter);
            return clienteParameter;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteCliente (@PathVariable Integer idCliente) {
        Optional<Cliente> cliente = instanceOfCliente.findById(idCliente);
        if (!cliente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        instanceOfCliente.delete(cliente.get());
    }

    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer idCliente) {
        Optional<Cliente> cliente = instanceOfCliente.findById(idCliente);
        if (cliente.isPresent()) {
            return cliente.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public List<Cliente> findCliente (Cliente filtroCliente) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtroCliente, matcher);
        List<Cliente> listaClientes = instanceOfCliente.findAll(example);
        return listaClientes;
    }
}
