package barbershop_api.barbershop.infra.segurity;

import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import org.hibernate.annotations.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SegurityConfigurantions {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain segurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/clientes/logout/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/clientes/cadastrar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/reservas/solicitar").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/reservas/alterar").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/clientes/listar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/clientes/alterar").authenticated()
                        .requestMatchers(HttpMethod.POST, "/barbeiro/cadastrar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/barbeiro/listar-por-id/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/barbeiro/alterar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/barbeiro/listar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/barbeiro/excluir/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/cortes/excluir/**").hasRole("BARBER")
                        .requestMatchers(HttpMethod.PUT, "/cortes/alterar").hasRole("BARBER")
                        .requestMatchers(HttpMethod.PUT, "/horario/cadastrar").hasRole("BARBER")
                        .requestMatchers(HttpMethod.POST, "/cortes/incluir").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cortes/listar").authenticated()
                        .requestMatchers(HttpMethod.GET, "/cortes/listar-por-id/**").authenticated()
                        .anyRequest().authenticated())

                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
