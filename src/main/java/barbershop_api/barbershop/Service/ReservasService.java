package barbershop_api.barbershop.Service;

import barbershop_api.barbershop.DTO.ReservasDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.ReservasCortesEntity;
import barbershop_api.barbershop.Repository.BarbeiroRepository;
import barbershop_api.barbershop.Repository.ReversasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class ReservasService {

    @Autowired
    private ReversasRepository reversasRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    public ResponseEntity<ReservasCortesEntity> cadastrar(ReservasDTO dto) throws DefaultExceptionHandler {

        try {

            if (!barbeiroRepository.existsById(dto.getCodBarbeiro())) {
                throw new RuntimeException("Erro: Barbeiro com ID " + dto.getCodBarbeiro() + " não encontrado.");
            }

            LocalTime horarioCorte = LocalTime.parse(dto.getHorarioCorte(), DateTimeFormatter.ofPattern("HH:mm"));

            boolean reserva = reversasRepository.findByCodBarbeiroAndDiaDaSemanaAndHorarioCorte(
                    dto.getCodBarbeiro(),
                    dto.getDiaDaSemana(),
                    horarioCorte
            ).isPresent();

            if (reserva) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma reserva para esse barbeiro nesse dia e horário!");
            }

            ReservasCortesEntity objeto = new ReservasCortesEntity();
            objeto.setCodCliente(dto.getCodCliente());
            objeto.setCodCorte(dto.getCodCorte());
            objeto.setCodBarbeiro(dto.getCodBarbeiro());
            objeto.setDiaDaSemana(dto.getDiaDaSemana());
            objeto.setHorarioCorte(horarioCorte);

            ReservasCortesEntity salvo = reversasRepository.save(objeto);

            return ResponseEntity.ok(salvo);

        } catch (Exception e) {
            if (e instanceof DefaultExceptionHandler) {
                throw e;
            } else {
                throw new DefaultExceptionHandler(e);
            }
        }

    }
}
