package barbershop_api.barbershop.Service;

import barbershop_api.barbershop.DTO.HorarioTrabalhoLoteDTO;
import barbershop_api.barbershop.Enums.DiaDaSemana;
import barbershop_api.barbershop.Model.BarbeiroEntity;
import barbershop_api.barbershop.Model.HorarioDeTrabalhoEntity;
import barbershop_api.barbershop.Repository.BarbeiroRepository;
import barbershop_api.barbershop.Repository.HorarioDeTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class HoraDeTrabalhoService {

    @Autowired
    private HorarioDeTrabalhoRepository horarioDeTrabalhoRepository;

    @Autowired
    private BarbeiroService barbeiroService;

    @Autowired
    private BarbeiroRepository barbeiroRepository;
    // No seu HorarioTrabalhoService.java

    @Transactional
    public void criarHorariosEmLote(HorarioTrabalhoLoteDTO horarioLoteDTO) {

        if (!barbeiroRepository.existsById(horarioLoteDTO.getCodBarbeiro())) {
            throw new RuntimeException("Erro: Barbeiro com ID " + horarioLoteDTO.getCodBarbeiro() + " não encontrado.");
        }

        horarioDeTrabalhoRepository.deleteByCodBarbeiro(horarioLoteDTO.getCodBarbeiro());

        LocalTime horaInicio = LocalTime.parse(horarioLoteDTO.getHoraInicio(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime horaFim = LocalTime.parse(horarioLoteDTO.getHoraFim(), DateTimeFormatter.ofPattern("HH:mm"));

        List<HorarioDeTrabalhoEntity> novosHorarios = new ArrayList<>();

        for(DiaDaSemana dia : horarioLoteDTO.getDias()){
            HorarioDeTrabalhoEntity novoHorario = HorarioDeTrabalhoEntity.builder()
                    .codBarbeiro(horarioLoteDTO.getCodBarbeiro())
                    .diaDaSemana(dia)
                    .horaInicio(horaInicio)
                    .horaFim(horaFim)
                    .build();

            novosHorarios.add(novoHorario);
        }

        if (!novosHorarios.isEmpty()) {
            horarioDeTrabalhoRepository.saveAll(novosHorarios);
        }

    }
}
