package barbershop_api.barbershop.Service;

import barbershop_api.barbershop.DTO.ClienteCadastroDTO;
import barbershop_api.barbershop.DTO.ClienteResponseDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.ClienteEntity;
import barbershop_api.barbershop.Repository.ClienteRepository;
import barbershop_api.barbershop.Role.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<ClienteResponseDTO> listar() throws DefaultExceptionHandler{
        try{
            return clienteRepository.findAll()
                    .stream()
                    .map(ClienteResponseDTO::new)
                    .toList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<ClienteEntity> cadastrar (ClienteCadastroDTO dto) throws DefaultExceptionHandler {
        try {
            if (clienteRepository.findByLogin(dto.getLogin()) != null) {
                return ResponseEntity.badRequest().build();
            }

            ClienteEntity objeto = new ClienteEntity();
            objeto.setNome(dto.getNome());
            objeto.setLogin(dto.getLogin());
            objeto.setSenha(passwordEncoder.encode(dto.getSenha()));
            objeto.setCpf(dto.getCpf());
            objeto.setEmail(dto.getEmail());
            objeto.setRole(UserRole.USER);

            clienteRepository.save(objeto);

            return ResponseEntity.ok().build()                                ;

        } catch (Exception e) {
            if (e instanceof DefaultExceptionHandler) {
                throw e;
            } else {
                throw new DefaultExceptionHandler(e);
            }
        }
    }

    public ClienteEntity buscarPorId(Long id) throws DefaultExceptionHandler{
        try{
            return clienteRepository.findById(id)
                    .orElseThrow(()-> new DefaultExceptionHandler(HttpStatus.BAD_REQUEST.value(),
                            "Operação inválida! Não existe nenhuma informação para este ID."));
        }catch (Exception e) {
            if (e instanceof DefaultExceptionHandler) {
                throw e;
            } else {
                throw new DefaultExceptionHandler(e);
            }
        }
    }

    public ClienteEntity alterar(ClienteResponseDTO dto) throws DefaultExceptionHandler{
        try{
            ClienteEntity objeto = buscarPorId(dto.getId());
            objeto.setNome(dto.getNome());
            objeto.setLogin(dto.getLogin());
            objeto.setEmail(dto.getEmail());
            objeto.setCpf(dto.getCpf());
            objeto.setTelefone(dto.getTelefone());

            return clienteRepository.save(objeto);
        }catch (Exception e) {
            if (e instanceof DefaultExceptionHandler) {
                throw e;
            } else {
                throw new DefaultExceptionHandler(e);
            }
        }
    }
}
