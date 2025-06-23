package barbershop_api.barbershop.Model;

import barbershop_api.barbershop.Enums.DiaDaSemana;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TBL_RESERVAS")
public class ReservasCortesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_RESERVA")
    private Long id;

    @Column(name = "COD_CLIENTE")
    private Long codCliente;

    @Column(name = "COD_CORTE")
    private Long codCorte;

    @Column(name = "COD_BARBEIRO")
    private Long codBarbeiro;

    @Column(name = "DIA_SAMANA")
    private DiaDaSemana diaDaSemana;

    @Column(name = "HORARIO_CORTE")
    private LocalTime horarioCorte;

    @Column(name = "STATUS")
    private Long codFluxo;

}
