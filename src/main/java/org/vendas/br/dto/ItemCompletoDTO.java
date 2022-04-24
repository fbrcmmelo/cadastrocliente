package org.vendas.br.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @AllArgsConstructor @Builder @NoArgsConstructor
public class ItemCompletoDTO {
    private String descricao;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
