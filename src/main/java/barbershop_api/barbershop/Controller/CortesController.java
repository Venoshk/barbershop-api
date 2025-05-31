package barbershop_api.barbershop.Controller;

import barbershop_api.barbershop.DTO.CortesDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.CortesEntity;
import barbershop_api.barbershop.Service.CortesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cortes")
public class CortesController {

    @Autowired
    CortesService cortesService;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<CortesEntity>> listar () throws DefaultExceptionHandler {
        List<CortesEntity> listar = cortesService.listar();
        return ResponseEntity.ok(listar);
    }


    @PostMapping(value = "/incluir")
    public ResponseEntity<CortesDTO> incluir(@RequestBody CortesDTO dto) throws DefaultExceptionHandler {
        CortesDTO salvar = cortesService.incluir(dto);
        return ResponseEntity.ok(salvar);
    }

    @DeleteMapping(value = "/excluir/{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) throws DefaultExceptionHandler {
        ResponseEntity excluir = cortesService.deletar(id);
        return ResponseEntity.ok().body(excluir);
    }
}
