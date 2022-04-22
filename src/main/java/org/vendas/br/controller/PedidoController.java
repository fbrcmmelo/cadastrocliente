package org.vendas.br.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.vendas.br.dto.PedidoCompletoDTO;
import org.vendas.br.dto.PedidoDTO;
import org.vendas.br.dto.StatusPedidoDTO;
import org.vendas.br.enums.StatusPedido;
import org.vendas.br.model.Pedido;
import org.vendas.br.service.PedidoService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService instanceOfPedidoService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save (@RequestBody @Valid  PedidoDTO dto) {
        Pedido pedido = instanceOfPedidoService.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public PedidoCompletoDTO findByIdPedido (@PathVariable Integer id) {
        Optional<PedidoCompletoDTO> pedidoCompleto = instanceOfPedidoService.findByIdFetchItem(id);
        if (!pedidoCompleto.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado !");
        }
        return pedidoCompleto.get();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("{id}")
    public void updateStatusPedido (@PathVariable Integer id,
                                    @RequestBody @Valid StatusPedidoDTO dto) {
        Optional<Pedido> pedido = instanceOfPedidoService.findById(id);
        if (!pedido.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        String status = dto.getStatus();
        instanceOfPedidoService.updateStatus(pedido.get(), StatusPedido.valueOf(status));
    }
}
