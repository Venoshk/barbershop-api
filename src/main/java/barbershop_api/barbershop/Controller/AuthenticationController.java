package barbershop_api.barbershop.Controller;

import barbershop_api.barbershop.DTO.AuthenticationDTO;
import barbershop_api.barbershop.DTO.CadastroDTO;
import barbershop_api.barbershop.DTO.LoginResponseDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.ClienteEntity;
import barbershop_api.barbershop.Repository.ClienteRepository;
import barbershop_api.barbershop.Service.UsuarioSessaoService;
import barbershop_api.barbershop.infra.segurity.TokenService;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {
        try {
            var userNamePassword = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getSenha());
            var auth = this.authenticationManager.authenticate(userNamePassword);

            var token = tokenService.genereteToken((UserDetails) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao Logar.");
        }
    }



}
