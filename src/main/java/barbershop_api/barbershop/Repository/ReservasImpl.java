package barbershop_api.barbershop.Repository;

import barbershop_api.barbershop.DTO.ConsultarReservasDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservasImpl {
    @PersistenceContext
    public EntityManager entityManager;

    public List<ConsultarReservasDTO> getListarReservas(Long id) {
        String sqlConsultarDadosBancarios = getSqlListReservas(id);

        Query query = entityManager.createNativeQuery(sqlConsultarDadosBancarios, "ConsultarReservasDTO").
                setParameter("id", id);

        return query.getResultList();
    }

    private String getSqlListReservas(Long id) {
        return """
                 SELECT
                 rs.cod_reserva,
                 rs.cod_barbeiro,
                 br.nome AS nome_barbeiro,
                 rs.cod_cliente ,
                 clt.nome AS nome_cliente,
                 rs.dia_samana,
                 rs.horario_corte,
                 rs.status
                 FROM
                 tbl_reservas rs
                INNER JOIN tbl_barbeiros br ON br.cod_barbeiro = rs.cod_barbeiro
                LEFT JOIN tbl_cliente clt ON clt.cod_cliente = rs.cod_cliente
                WHERE
                    rs.cod_cliente = :id;
                """;
    }

}
