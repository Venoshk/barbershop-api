package barbershop_api.barbershop.Repository;

import barbershop_api.barbershop.Model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {
    ClienteEntity findByLogin(String login);
}
