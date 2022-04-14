package org.vendas.br.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Dto (Data Transfer Object) ele nos serve como uma copia do nosso objeto que queremos persistir, para trafegar sobre as requisiçoes
//e então convertidas no nosso objeto de fato para ser retornado o objeto na response, Auxilia no entendimento da regra de negócio
//e torna maior a integridade da classe, mas tem aumento na complexidade de codigo.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {
    private Integer produto;
    private Integer quantidade;
}
