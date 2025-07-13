package barbershop_api.barbershop.Validation;

import barbershop_api.barbershop.DTO.ReservasDTO;
import barbershop_api.barbershop.Enums.DiaDaSemana;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Repository.BarbeiroRepository;
import barbershop_api.barbershop.Repository.ClienteRepository;
import barbershop_api.barbershop.Repository.CortesRepository;
import barbershop_api.barbershop.Repository.ReservasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class ReservaValidator {

    private final BarbeiroRepository barbeiroRepository;
    private final ClienteRepository clienteRepository;
    private final CortesRepository cortesRepository;
    private final ReservasRepository reversasRepository;

    public ReservaValidator(BarbeiroRepository barbeiroRepository,
                            ClienteRepository clienteRepository,
                            CortesRepository cortesRepository,
                            ReservasRepository reversasRepository ) {
        this.barbeiroRepository = barbeiroRepository;
        this.clienteRepository = clienteRepository;
        this.cortesRepository = cortesRepository;
        this.reversasRepository = reversasRepository;
    }

    public void validar(ReservasDTO dto) throws DefaultExceptionHandler{
        validarCampoObrigatorios(dto);
        validarBarbeiroExistente(dto.getCodBarbeiro());
        validarClienteExiste(dto.getCodCliente());
        validarCorteExiste(dto.getCodCorte());
        validarHorario(dto.getHorarioCorte());
        validarDiaDaSemana(String.valueOf(dto.getDiaDaSemana()));
        validarHorarioDaSolicitacaoDeReserva(dto);
    }

    private void validarCampoObrigatorios(ReservasDTO dto) throws DefaultExceptionHandler {
        if (dto.getCodBarbeiro() == null || dto.getCodCliente() == null || dto.getCodCorte() == null || dto.getDiaDaSemana() == null || dto.getHorarioCorte() == null) {
            throw new DefaultExceptionHandler(HttpStatus.NO_CONTENT.value(), "Todos os campos são obrigatórios.");
        }
    }

    private void validarBarbeiroExistente(Long codBarbeiro) {
        if (!barbeiroRepository.existsById(codBarbeiro)) {
            throw new RuntimeException("Barbeiro não encontrado.");
        }
    }

    private void validarClienteExiste(Long codCliente) {
        if (!clienteRepository.existsById(codCliente)) {
            throw new RuntimeException("Cliente não encontrado.");
        }
    }

    private void validarCorteExiste(Long codCorte) {
        if (!cortesRepository.existsById(codCorte)) {
            throw new RuntimeException("Corte não encontrado.");
        }
    }

    private Date validarHorario(Date horario) {
        try {
            return horario;
        } catch (Exception e) {
            throw new RuntimeException("Formato de horário inválido.");
        }
    }

    private void validarDiaDaSemana(String dia) {
        try {
            DiaDaSemana.valueOf(dia.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Dia da semana inválido.");
        }
    }

    private void validarHorarioDaSolicitacaoDeReserva(ReservasDTO dto){

        Date horario = dto.getHorarioCorte();

        try{
            if (horario.before(new Date())) {
                throw new RuntimeException("Não é possível fazer reservas para datas ou horários no passado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Formato de horário inválido.");
        }
    }

}
