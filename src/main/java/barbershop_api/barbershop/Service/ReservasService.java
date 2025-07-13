package barbershop_api.barbershop.Service;

import barbershop_api.barbershop.DTO.ConsultarReservasDTO;
import barbershop_api.barbershop.DTO.ReservasDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.ReservasCortesEntity;
import barbershop_api.barbershop.Repository.BarbeiroRepository;
import barbershop_api.barbershop.Repository.ReservasImpl;
import barbershop_api.barbershop.Repository.ReservasRepository;
import barbershop_api.barbershop.Validation.ReservaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class ReservasService {

    @Autowired
    private ReservasRepository reversasRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private ReservaValidator reservaValidator;

    @Autowired
    private ReservasImpl reservas;


    public ResponseEntity<ReservasCortesEntity> cadastrar(ReservasDTO dto) throws DefaultExceptionHandler {

        try {

            reservaValidator.validar(dto);

            boolean reserva = reversasRepository.findByCodBarbeiroAndDiaDaSemanaAndHorarioCorte(
                    dto.getCodBarbeiro(),
                    dto.getDiaDaSemana(),
                    dto.getHorarioCorte()
            ).isPresent();

            if (reserva) {
                throw new DefaultExceptionHandler(HttpStatus.CONFLICT.value(), "Já existe uma reserva para esse barbeiro nesse dia e horário!");
            }

            ReservasCortesEntity objeto = new ReservasCortesEntity();
            objeto.setCodCliente(dto.getCodCliente());
            objeto.setCodCorte(dto.getCodCorte());
            objeto.setCodBarbeiro(dto.getCodBarbeiro());
            objeto.setDiaDaSemana(dto.getDiaDaSemana());
            objeto.setHorarioCorte(dto.getHorarioCorte());
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

            reservaValidator.validar(dto);

            ReservasCortesEntity objeto =  buscarPorId(dto.getId());
            objeto.setCodBarbeiro(dto.getCodBarbeiro());
            objeto.setCodCorte(dto.getCodCorte());
            objeto.setDiaDaSemana(dto.getDiaDaSemana());
            objeto.setHorarioCorte(new Date());
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


    public List<ConsultarReservasDTO> buscarPorReservas(Long id) throws DefaultExceptionHandler{
        try{
            return reservas.getListarReservas(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}



