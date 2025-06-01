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

    @GetMapping(value = "/listar-por-id/{id}")
    public ResponseEntity<CortesEntity> listarPorId(@PathVariable("id") Long id) throws DefaultExceptionHandler {
        CortesEntity listar = cortesService.buscarPorId(id);
        return ResponseEntity.ok(listar);
    }


    @PostMapping(value = "/incluir")
    public ResponseEntity<CortesEntity> incluir(@RequestBody CortesDTO dto) throws DefaultExceptionHandler {
       return cortesService.incluir(dto);
    }

    @DeleteMapping(value = "/excluir/{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) throws DefaultExceptionHandler {
        ResponseEntity excluir = cortesService.deletar(id);
        return ResponseEntity.ok().body(excluir);
    }

    @PutMapping(value = "/alterar")
    public ResponseEntity<CortesEntity> alterar(@RequestBody CortesDTO dto) throws DefaultExceptionHandler{
        CortesEntity listar = cortesService.alterar(dto);
        return ResponseEntity.ok().body(listar);
    }


}
