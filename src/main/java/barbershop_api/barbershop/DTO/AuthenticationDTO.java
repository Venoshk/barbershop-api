package barbershop_api.barbershop.DTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationDTO {

    private String login;
    private String senha;
}
