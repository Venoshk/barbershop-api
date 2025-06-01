package barbershop_api.barbershop.Controller;

import barbershop_api.barbershop.DTO.BarbeiroDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.BarbeiroEntity;
import barbershop_api.barbershop.Service.BarbeiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/barbeiro")
public class BarbeiroController {

    @Autowired
    private BarbeiroService barbeiroService;

    @PostMapping("/cadastrar")
    public ResponseEntity<BarbeiroEntity> cadastrar(@RequestBody @Valid BarbeiroDTO dto) throws  DefaultExceptionHandler {
        return barbeiroService.cadastrar(dto);
    }
}
