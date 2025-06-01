package barbershop_api.barbershop.Service;

import barbershop_api.barbershop.DTO.AuthenticationDTO;
import barbershop_api.barbershop.Model.BarbeiroEntity;
import barbershop_api.barbershop.Model.UserEntity;
import barbershop_api.barbershop.Repository.BarbeiroRepository;
import barbershop_api.barbershop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(username);

        if(user != null){
            return user;
        }

        BarbeiroEntity barbeiro = barbeiroRepository.findByLogin(username);
        if (barbeiro != null) {
            return barbeiro;
        }

        throw new UsernameNotFoundException("Usuário ou barbeiro não encontrado com login: " + username);

    }

}
