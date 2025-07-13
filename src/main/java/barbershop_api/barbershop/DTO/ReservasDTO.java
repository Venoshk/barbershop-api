package barbershop_api.barbershop.DTO;

import barbershop_api.barbershop.Enums.DiaDaSemana;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservasDTO {
    private Long id;
    private Long codCliente;
    private Long codCorte;
    private Long codBarbeiro;
    private Long codFluxo;
    private DiaDaSemana diaDaSemana;
    private Date horarioCorte;
}
