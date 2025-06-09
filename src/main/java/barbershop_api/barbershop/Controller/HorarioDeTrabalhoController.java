package barbershop_api.barbershop.Controller;

import barbershop_api.barbershop.DTO.HorarioTrabalhoLoteDTO;
import barbershop_api.barbershop.Model.HorarioDeTrabalhoEntity;
import barbershop_api.barbershop.Service.HoraDeTrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/horario")
public class HorarioDeTrabalhoController {

    @Autowired
    private HoraDeTrabalhoService horaDeTrabalhoService;

    @PostMapping(value = "/cadastrar")
    // No seu HorarioTrabalhoController.java
    public ResponseEntity<Void> criarHorariosEmLote(@RequestBody HorarioTrabalhoLoteDTO horarioLoteDTO) {
        horaDeTrabalhoService.criarHorariosEmLote(horarioLoteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
