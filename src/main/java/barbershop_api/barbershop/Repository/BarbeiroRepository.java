package barbershop_api.barbershop.Repository;

import barbershop_api.barbershop.Model.BarbeiroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface BarbeiroRepository extends JpaRepository<BarbeiroEntity, Long> {
    BarbeiroEntity findByLogin(String login);
}
