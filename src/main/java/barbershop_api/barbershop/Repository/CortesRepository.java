package barbershop_api.barbershop.Repository;

import barbershop_api.barbershop.Model.CortesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CortesRepository extends JpaRepository<CortesEntity, Long> {
}
