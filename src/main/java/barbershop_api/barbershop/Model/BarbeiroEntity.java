package barbershop_api.barbershop.Model;

import barbershop_api.barbershop.Role.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_BARBEIROS")
public class BarbeiroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_BARBEIRO")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "DATA_NASCIMENTO")
    private Date dataNascimento;

    @Column(name = "CPF")
    private Long cpf;

    @Column(name = "EMAIL")
    private String email;

    private UserRole role;

}
