package barbershop_api.barbershop.Controller;

import barbershop_api.barbershop.DTO.AuthenticationDTO;
import barbershop_api.barbershop.DTO.CadastroDTO;
import barbershop_api.barbershop.DTO.LoginResponseDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.ClienteEntity;
import barbershop_api.barbershop.Repository.ClienteRepository;
import barbershop_api.barbershop.infra.segurity.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private ClienteRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) throws DefaultExceptionHandler {
       try{
           var userNamePassword = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getSenha());
           var auth = this.authenticationManager.authenticate(userNamePassword);

           var userDetails = (UserDetails) auth.getPrincipal();
           var token = tokenService.genereteToken(userDetails);

           return ResponseEntity.ok(new LoginResponseDTO(token));
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }
    }

    @PostMapping(value = "/cadastro")
    public ResponseEntity cadastro(@RequestBody @Valid CadastroDTO dto) throws DefaultExceptionHandler{
       try{
           if(userRepository.findByLogin(dto.getLogin()) != null) return ResponseEntity.badRequest().build();

           String encryptePassword = new BCryptPasswordEncoder().encode(dto.getSenha());
           ClienteEntity user = new ClienteEntity(dto.getLogin(), encryptePassword, dto.getRole());

           userRepository.save(user);

           return ResponseEntity.ok().build();
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }
    }
}
