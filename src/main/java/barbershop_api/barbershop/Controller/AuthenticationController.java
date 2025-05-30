package barbershop_api.barbershop.Controller;

import barbershop_api.barbershop.DTO.AuthenticationDTO;
import barbershop_api.barbershop.DTO.CadastroDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.UserEntity;
import barbershop_api.barbershop.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) throws DefaultExceptionHandler {
       try{
           var userNamePassword = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getSenha());
           var auth = this.authenticationManager.authenticate(userNamePassword);

           return ResponseEntity.ok().build();
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }
    }

    @PostMapping(value = "/cadastro")
    public ResponseEntity cadastro(@RequestBody @Valid CadastroDTO dto) throws DefaultExceptionHandler{
       try{
           if(userRepository.findByLogin(dto.getLogin()) != null) return ResponseEntity.badRequest().build();

           String encryptePassword = new BCryptPasswordEncoder().encode(dto.getSenha());
           UserEntity user = new UserEntity(dto.getLogin(), encryptePassword, dto.getRole());

           userRepository.save(user);

           return ResponseEntity.ok().build();
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }
    }
}
