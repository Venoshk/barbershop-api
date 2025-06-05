package barbershop_api.barbershop.DTO;

import barbershop_api.barbershop.Enums.UserRole;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CadastroDTO {

    private String login;
    private String senha;
    private UserRole role;
}
