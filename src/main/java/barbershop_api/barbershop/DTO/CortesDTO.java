package barbershop_api.barbershop.DTO;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CortesDTO {
    private Long id;
    private Double preco;
    private String desc;
    private String categoria;
    private Boolean ativo;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private byte[] imagem;
}
