package org.vendas.br.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.vendas.br.dto.ItemPedidoDTO;
import org.vendas.br.exceptions.ExceptionsRules;
import org.vendas.br.model.ItemPedido;
import org.vendas.br.model.Pedido;
import org.vendas.br.model.Produto;
import org.vendas.br.repository.ItemPedidoRepository;
import org.vendas.br.repository.ProdutoRepository;
import org.vendas.br.service.ItemPedidoService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private final ItemPedidoRepository instanceOfItemPedido;
    private final ProdutoRepository instanceOfProdutoRepository;

    @Override
    public List<ItemPedido> salvarTodos(Pedido pedido, List<ItemPedidoDTO> itens) {
        List<ItemPedido> listaItens = new ArrayList<>();

        if (itens.isEmpty()){
            throw new ExceptionsRules("Lista Vazia !");
        }

        for (ItemPedidoDTO item: itens) {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(item.getQuantidade());
            Optional<Produto> produto = instanceOfProdutoRepository.findById(item.getProduto());

            if (!produto.isPresent()){
                throw new ExceptionsRules("Produto Inválido !");
            }
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto.get());
            instanceOfItemPedido.save(itemPedido);
            listaItens.add(itemPedido);
        }
        return listaItens;
    }

    @Override
    public ItemPedido updateItem(ItemPedido item) {
        return null;
    }

    @Override
    public void delete(ItemPedido item) {

    }

    @Override
    public List<ItemPedido> findAllItens(Example example) {
        return null;
    }

    @Override
    public ItemPedido findItemPedidoById(Integer id) {
        return null;
    }
}
