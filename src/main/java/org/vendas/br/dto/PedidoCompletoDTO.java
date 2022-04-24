package org.vendas.br.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PedidoCompletoDTO {
    private Integer id;
    private String nomeCliente;
    private String cpfCliente;
    private String data;
    private String status;
    private BigDecimal total;
    private List<ItemCompletoDTO> itens;
}
