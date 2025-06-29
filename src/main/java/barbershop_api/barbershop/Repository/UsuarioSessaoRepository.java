package barbershop_api.barbershop.Repository;

import barbershop_api.barbershop.Model.UsuarioSessaoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UsuarioSessaoRepository extends JpaRepository<UsuarioSessaoEntity, Long> {
    List<UsuarioSessaoEntity> findByStatusAndDataValidadeTokenBefore(String status, Date tempoLimite);

    @Query("SELECT u FROM UsuarioSessaoEntity u WHERE u.codUsuario = :codUsuario AND u.status = :status ORDER BY u.dataLogin DESC")
    List<UsuarioSessaoEntity> findBySessaoAtivaMaisRecente(
            @Param("codUsuario") Long codUsuario,
            @Param("status") String status,
            @Param("pageable") Pageable pageable
    );
}
