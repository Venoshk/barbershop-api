package barbershop_api.barbershop.Controller;

import barbershop_api.barbershop.DTO.ClienteCadastroDTO;
import barbershop_api.barbershop.DTO.ClienteResponseDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.ClienteEntity;
import barbershop_api.barbershop.Service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<ClienteResponseDTO>> listar() throws DefaultExceptionHandler{
        List<ClienteResponseDTO> listar = clienteService.listar();
        return ResponseEntity.ok(listar);
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<ClienteEntity> cadastrar(@RequestBody @Valid ClienteCadastroDTO dto) throws DefaultExceptionHandler{
        return clienteService.cadastrar(dto);
    }

    @PutMapping(value = "/altarar")
    public ResponseEntity<ClienteEntity> alterar(@RequestBody @Valid ClienteResponseDTO dto) throws DefaultExceptionHandler{
        ClienteEntity listar = clienteService.alterar(dto);
        return ResponseEntity.ok().build();
    }
}
