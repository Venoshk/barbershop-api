package barbershop_api.barbershop.DTO;

import barbershop_api.barbershop.Role.UserRole;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClienteCadastroDTO {
    private Long id;
    private String nome;
    private String senha;
    private String login;
    private String cpf;
    private Long telefone;
    private String email;
    private Date dataNascimento;
    private UserRole role;
}
