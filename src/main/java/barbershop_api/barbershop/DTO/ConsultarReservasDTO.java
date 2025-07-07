package barbershop_api.barbershop.DTO;

import barbershop_api.barbershop.Enums.DiaDaSemana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@SqlResultSetMapping(
        name = "ConsultarReservasDTO",
        classes = {
                @ConstructorResult(
                        targetClass = ConsultarReservasDTO.class,
                        columns = {
                                @ColumnResult(name = "COD_RESERVA", type = Long.class),
                                @ColumnResult(name = "COD_BARBEIRO", type = Long.class),
                                @ColumnResult(name = "NOME_BARBEIRO", type = String.class),
                                @ColumnResult(name = "COD_CLIENTE", type = Long.class),
                                @ColumnResult(name = "NOME_CLIENTE", type = String.class),
                                @ColumnResult(name = "DIA_SAMANA", type = DiaDaSemana.class),
                                @ColumnResult(name = "HORARIO_CORTE", type = Date.class),
                                @ColumnResult(name = "STATUS", type = Long.class)

                        }
                )
        }
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class ConsultarReservasDTO {
    @Id
    private Long id;
    private Long codBarbeiro;
    private String nomBarbeiro;
    private Long codCliente;
    private String nomCliete;
    private DiaDaSemana diaDaSemana;
    private Date horarioCorte;
    private Long status;

}
