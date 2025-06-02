package barbershop_api.barbershop.DTO;

import barbershop_api.barbershop.Model.ClienteEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private Long telefone;
    private String login;
    private String email;
    private String cpf;

    public ClienteResponseDTO(ClienteEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.login = entity.getLogin();
        this.email = entity.getEmail();
        this.cpf = entity.getCpf();
        this.telefone = entity.getTelefone();
    }
}
