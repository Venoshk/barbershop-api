package barbershop_api.barbershop.Model;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;

import javax.lang.model.element.Name;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "TBL_CORTES")
public class CortesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CORTE")
    private Long id;

    @Column(name = "PRECO_CORTE")
    private Double preco;

    @Column(name = "DESCRICAO")
    private String desc;

    @Column(name = "CATEGORIA")
    private String categoria;

    @Column(name = "ATIVO")
    private Boolean ativo;

    @Column(name = "DATA_CRIACAO")
    private Date dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private Date dataAtualizacao;


}
