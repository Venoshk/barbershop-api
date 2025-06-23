package barbershop_api.barbershop.Controller;

import barbershop_api.barbershop.DTO.ReservasDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.ReservasCortesEntity;
import barbershop_api.barbershop.Service.ReservasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reservas")
public class ReservasController {

    @Autowired
    private ReservasService reservasService;

    @PostMapping(value = "/solicitar")
    public ResponseEntity<ReservasCortesEntity> cadastrar (@RequestBody @Valid ReservasDTO dto) throws DefaultExceptionHandler{
        return reservasService.cadastrar(dto);
    }

    @PutMapping(value = "/alterar")
    public ResponseEntity<ReservasCortesEntity> altarar(@RequestBody @Valid ReservasDTO dto) throws DefaultExceptionHandler{
        ReservasCortesEntity listar = reservasService.alterarReserva(dto);
        return ResponseEntity.ok().build();
    }
}
