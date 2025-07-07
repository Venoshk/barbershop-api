package barbershop_api.barbershop.Validation;

import barbershop_api.barbershop.DTO.ReservasDTO;
import barbershop_api.barbershop.Enums.DiaDaSemana;
import barbershop_api.barbershop.Repository.BarbeiroRepository;
import barbershop_api.barbershop.Repository.ClienteRepository;
import barbershop_api.barbershop.Repository.CortesRepository;
import barbershop_api.barbershop.Repository.ReservasRepository;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    public void validar(ReservasDTO dto) {
        validarCampoObrigatorios(dto);
        validarBarbeiroExistente(dto.getCodBarbeiro());
        validarClienteExiste(dto.getCodCliente());
        validarCorteExiste(dto.getCodCorte());
        validarHorario(dto.getHorarioCorte());
        validarDiaDaSemana(String.valueOf(dto.getDiaDaSemana()));
        validarHorarioDaSolicitacaoDeReserva(dto);
    }

    private void validarCampoObrigatorios(ReservasDTO dto) {
        if (dto.getCodBarbeiro() == null || dto.getCodCliente() == null || dto.getCodCorte() == null || dto.getDiaDaSemana() == null || dto.getHorarioCorte() == null) {
            throw new RuntimeException("Todos os campos são obrigatórios.");
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

    private void validarHorario(String horario) {
        try {
            LocalTime.parse(horario, DateTimeFormatter.ofPattern("HH:mm"));
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

        LocalTime horario = LocalTime.parse(dto.getHorarioCorte(), DateTimeFormatter.ofPattern("HH:mm"));

        try{
            if(horario.isBefore(LocalTime.of(8, 0)) || horario.isAfter(LocalTime.of(18, 0))){
                throw new RuntimeException("Horário fora do expediente. Permitido apenas entre 08:00 e 18:00.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Formato de horário inválido.");
        }
    }

}
