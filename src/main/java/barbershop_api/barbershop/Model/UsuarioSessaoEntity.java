package barbershop_api.barbershop.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USUARIO_SESSAO")
public class UsuarioSessaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COD_USUARIO", nullable = false)
    private Long codUsuario;

    @Column(name = "NOM_USUARIO")
    private String nomUsuario;

    @Column(name = "DATA_LOGIN", nullable = false)
    private Date dataLogin;

    @Column(name = "DATA_LOGOUT")
    private Date dataLogout;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "DATA_VALIDADE_TOKEN")
    private Date dataValidadeToken;

}
