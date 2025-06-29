package barbershop_api.barbershop.Service;

import barbershop_api.barbershop.Model.BarbeiroEntity;
import barbershop_api.barbershop.Model.ClienteEntity;
import barbershop_api.barbershop.Repository.BarbeiroRepository;
import barbershop_api.barbershop.Repository.ClienteRepository;
import barbershop_api.barbershop.infra.segurity.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private ClienteRepository ClienteRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private UsuarioSessaoService usuarioSessaoService;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClienteEntity user = ClienteRepository.findByLogin(username);

        if(user != null){
            return user;
        }

        BarbeiroEntity barbeiro = barbeiroRepository.findByLogin(username);
        Date dataDeValidadeDoToken = tokenService.calcularDataDeExpiracaoParaNovoToken();
        if (barbeiro != null) {
            usuarioSessaoService.registrarLogin(barbeiro, dataDeValidadeDoToken);
            return barbeiro;
        }

        throw new UsernameNotFoundException("Usuário ou barbeiro não encontrado com login: " + username);

    }



}
