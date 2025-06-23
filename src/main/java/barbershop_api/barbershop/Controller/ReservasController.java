package barbershop_api.barbershop.Controller;

import barbershop_api.barbershop.DTO.ReservasDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.ReservasCortesEntity;
import barbershop_api.barbershop.Service.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/reservas")
public class ReservasController {

    @Autowired
    private ReservasService reservasService;

    @PostMapping(value = "/solicitar")
    public ResponseEntity<ReservasCortesEntity> cadastrar (@RequestBody ReservasDTO dto) throws DefaultExceptionHandler{
        return reservasService.cadastrar(dto);
    }
}
