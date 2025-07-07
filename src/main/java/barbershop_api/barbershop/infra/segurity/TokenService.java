package barbershop_api.barbershop.infra.segurity;
import barbershop_api.barbershop.Model.ClienteEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${jwt.expirationTimeAccessToken}")
    private long expirationTimeAccessToken;

    public String genereteToken(UserDetails userDetails){
        try{
            ClienteEntity cliente = (ClienteEntity) userDetails;

            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(userDetails.getUsername())
                    .withClaim("userId", cliente.getId())
                    .withClaim("roles", userDetails.getAuthorities().stream()
                            .map(role -> role.getAuthority())
                            .toList())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expirationTimeAccessToken))
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro para gerar o token", exception);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    // --- PASSO EXTRA (RECOMENDADO): Crie um método para extrair o ID ---

    /**
     * Extrai o ID do usuário (userId) de um token JWT.
     * @param token O token JWT (sem o prefixo "Bearer ").
     * @return O ID do usuário como um Long.
     */
    public Long extractUserId(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getClaim("userId").asLong(); // Pega a claim "userId" e converte para Long
        } catch (JWTVerificationException exception) {
            // Pode lançar uma exceção ou retornar null dependendo da sua estratégia de erro
            return null;
        }
    }

    public Date calcularDataDeExpiracaoParaNovoToken() {
        return new Date(System.currentTimeMillis() + expirationTimeAccessToken);
    }
}