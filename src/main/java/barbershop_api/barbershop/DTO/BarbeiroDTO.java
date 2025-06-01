package barbershop_api.barbershop.DTO;

import barbershop_api.barbershop.Role.UserRole;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BarbeiroDTO {
    private Long id;
    private String nome;
    private String senha;
    private String login;
    private Date dataNascimento;
    private Long cpf;
    private String email;
    private UserRole role;
}
