package barbershop_api.barbershop.DTO;

import barbershop_api.barbershop.Role.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@SqlResultSetMapping(
        name = "ClientesDTOMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ClienteDTO.class,
                        columns = {
                                @ColumnResult(name = "COD_CLIENTE"),
                                @ColumnResult(name = "NOME"),
                                @ColumnResult(name = "LOGIN"),
                                @ColumnResult(name = "CPF"),
                                @ColumnResult(name = "EMAIL"),
                                @ColumnResult(name = "DATA_NASCIMENTO"),
                        }
                )
        }
)

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class ClienteDTO implements Serializable {

    @Id
    private Long id;
    private String nome;
    private String login;
    private String cpf;
    private Long telefone;
    private String email;
    private Date dataNascimento;
    private UserRole role;
}
