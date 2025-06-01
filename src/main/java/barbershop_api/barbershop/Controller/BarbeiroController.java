package barbershop_api.barbershop.Controller;

import barbershop_api.barbershop.DTO.BarbeiroDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.BarbeiroEntity;
import barbershop_api.barbershop.Service.BarbeiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/barbeiro")
public class BarbeiroController {

    @Autowired
    private BarbeiroService barbeiroService;

    @GetMapping("/listar-por-id/{id}")
    public ResponseEntity<BarbeiroEntity> listarPorId(@PathVariable("id") Long id) throws DefaultExceptionHandler {
        BarbeiroEntity listar = barbeiroService.buscarPorId(id);
        return ResponseEntity.ok(listar);
    }

    @PutMapping("/alterar")
    public ResponseEntity<BarbeiroEntity> alterar(@RequestBody @Valid BarbeiroDTO dto) throws DefaultExceptionHandler {
        BarbeiroEntity listar = barbeiroService.alterar(dto);
        return ResponseEntity.ok(listar);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<BarbeiroEntity> cadastrar(@RequestBody @Valid BarbeiroDTO dto) throws  DefaultExceptionHandler {
        return barbeiroService.cadastrar(dto);
    }


}
