package barbershop_api.barbershop.Repository;

import barbershop_api.barbershop.Model.HorarioDeTrabalhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioDeTrabalhoRepository extends JpaRepository<HorarioDeTrabalhoEntity, Long> {
    void deleteByCodBarbeiro(Long codBarbeiro);
}
