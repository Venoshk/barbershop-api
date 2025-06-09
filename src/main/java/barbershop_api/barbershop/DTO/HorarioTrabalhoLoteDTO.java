package barbershop_api.barbershop.DTO;

import barbershop_api.barbershop.Enums.DiaDaSemana;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HorarioTrabalhoLoteDTO {
    private Long id;
    private Long codBarbeiro;
    private List<DiaDaSemana> dias;
    private String horaInicio;
    private String horaFim;
}
