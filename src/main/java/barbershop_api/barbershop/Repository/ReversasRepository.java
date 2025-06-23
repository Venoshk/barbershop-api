package barbershop_api.barbershop.Repository;

import barbershop_api.barbershop.Enums.DiaDaSemana;
import barbershop_api.barbershop.Model.ReservasCortesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface ReversasRepository extends JpaRepository<ReservasCortesEntity, Long > {
    Optional<ReservasCortesEntity> findByCodBarbeiroAndDiaDaSemanaAndHorarioCorte(Long codBarbeiro, DiaDaSemana dia, LocalTime horario);
}
