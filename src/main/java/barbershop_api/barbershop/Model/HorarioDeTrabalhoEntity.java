package barbershop_api.barbershop.Model;

import barbershop_api.barbershop.Enums.DiaDaSemana;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "TBL_HORARIO_TRABALHO")
public class HorarioDeTrabalhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_HORARIO_TRABALHO")
    private Long id;

    @Column(name = "COD_BARBEIRO")
    private Long codBarbeiro;

    @Column(name = "DIA_SEMANA")
    private DiaDaSemana diaDaSemana;

    @Column(name = "HORA_INICIO")
    private LocalTime horaInicio;

    @Column(name = "HORA_FIM")
    private LocalTime horaFim;


}
