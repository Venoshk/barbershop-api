package barbershop_api.barbershop.Repository;

import barbershop_api.barbershop.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserDetails findByLogin(String login);
}
