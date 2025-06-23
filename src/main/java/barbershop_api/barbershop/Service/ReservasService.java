package barbershop_api.barbershop.Service;

import barbershop_api.barbershop.DTO.ReservasDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.ClienteEntity;
import barbershop_api.barbershop.Model.ReservasCortesEntity;
import barbershop_api.barbershop.Repository.BarbeiroRepository;
import barbershop_api.barbershop.Repository.ReversasRepository;
import barbershop_api.barbershop.Validation.ReservaValidator;
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

    @Autowired
    private ReservaValidator reservaValidator;


    public ResponseEntity<ReservasCortesEntity> cadastrar(ReservasDTO dto) throws DefaultExceptionHandler {

        try {

            reservaValidator.validar(dto);

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
            objeto.setCodFluxo(dto.getCodFluxo());

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

    public ReservasCortesEntity alterarReserva(ReservasDTO dto) throws DefaultExceptionHandler{
        try{

            LocalTime horarioCorte = LocalTime.parse(dto.getHorarioCorte(), DateTimeFormatter.ofPattern("HH:mm"));

            reservaValidator.validar(dto);

            ReservasCortesEntity objeto =  buscarPorId(dto.getId());
            objeto.setCodBarbeiro(dto.getCodBarbeiro());
            objeto.setCodCorte(dto.getCodCorte());
            objeto.setDiaDaSemana(dto.getDiaDaSemana());
            objeto.setHorarioCorte(horarioCorte);
            objeto.setDiaDaSemana(dto.getDiaDaSemana());
            objeto.setCodFluxo(dto.getCodFluxo());

            return reversasRepository.save(objeto);
        } catch (Exception e) {
            if (e instanceof DefaultExceptionHandler) {
                throw e;
            } else {
                throw new DefaultExceptionHandler(e);
            }
        }
    }

    public ReservasCortesEntity buscarPorId(Long id) throws DefaultExceptionHandler{
        try{
            return reversasRepository.findById(id)
                    .orElseThrow(()-> new DefaultExceptionHandler(HttpStatus.BAD_REQUEST.value(),
                            "Operação inválida! Não existe nenhuma informação para este ID."));
        }catch (Exception e) {
            if (e instanceof DefaultExceptionHandler) {
                throw e;
            } else {
                throw new DefaultExceptionHandler(e);
            }
        }
    }
}



