package barbershop_api.barbershop.Service;


import barbershop_api.barbershop.DTO.BarbeiroDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.BarbeiroEntity;
import barbershop_api.barbershop.Repository.BarbeiroRepository;
import barbershop_api.barbershop.Role.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BarbeiroService {

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<BarbeiroEntity> cadastrar (BarbeiroDTO dto) throws DefaultExceptionHandler {
        try{
            if(barbeiroRepository.findByLogin(dto.getLogin()) != null){
                return ResponseEntity.badRequest().build();
            }

            BarbeiroEntity objeto = new BarbeiroEntity();
            objeto.setNome(dto.getNome());
            objeto.setLogin(dto.getLogin());
            objeto.setSenha(passwordEncoder.encode(dto.getSenha()));
            objeto.setCpf(dto.getCpf());
            objeto.setEmail(dto.getEmail());
            objeto.setRole(UserRole.BARBER);

            BarbeiroEntity salvo = barbeiroRepository.save(objeto);

            return ResponseEntity.ok(salvo);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
