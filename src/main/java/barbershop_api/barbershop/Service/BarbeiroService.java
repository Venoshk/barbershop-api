package barbershop_api.barbershop.Service;


import barbershop_api.barbershop.DTO.BarbeiroDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.BarbeiroEntity;
import barbershop_api.barbershop.Repository.BarbeiroRepository;
import barbershop_api.barbershop.Role.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarbeiroService {

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<BarbeiroEntity> listar() throws DefaultExceptionHandler{
        try{
          return barbeiroRepository.findAll();
        }catch (Exception e) {
            if (e instanceof DefaultExceptionHandler) {
                throw e;
            } else {
                throw new DefaultExceptionHandler(e);
            }
        }
    }


    public ResponseEntity<BarbeiroEntity> cadastrar (BarbeiroDTO dto) throws DefaultExceptionHandler {
        try{
            if(barbeiroRepository.findByLogin(dto.getLogin()) != null){
                return ResponseEntity.badRequest().build();
            }

            BarbeiroEntity objeto = new BarbeiroEntity();
            objeto.setNome(dto.getNome());
            objeto.setLogin(dto.getLogin());
            objeto.setSenha(passwordEncoder.encode(dto.getSenha()));
            objeto.setCpf(dto.getCpf());
            objeto.setEmail(dto.getEmail());
            objeto.setRole(UserRole.BARBER);

            BarbeiroEntity salvo = barbeiroRepository.save(objeto);

            return ResponseEntity.ok(salvo);

        } catch (Exception e) {
            if (e instanceof DefaultExceptionHandler) {
                throw e;
            } else {
                throw new DefaultExceptionHandler(e);
            }
        }
    }

    public BarbeiroEntity buscarPorId(Long id) throws DefaultExceptionHandler{
       try{
           return barbeiroRepository.findById(id)
                   .orElseThrow(()-> new DefaultExceptionHandler(HttpStatus.BAD_REQUEST.value(),
                           "Operação inválida! Não existe nenhuma informação para este ID."));
       }
        catch (Exception e) {
            if (e instanceof DefaultExceptionHandler) {
                throw e;
            } else {
                throw new DefaultExceptionHandler(e);
            }
        }
    }

    public BarbeiroEntity alterar(BarbeiroDTO dto) throws DefaultExceptionHandler {
        try{
            BarbeiroEntity objeto = buscarPorId(dto.getId());
            objeto.setNome(dto.getNome());
            objeto.setLogin(dto.getLogin());
            objeto.setDataNascimento(dto.getDataNascimento());
            objeto.setCpf(dto.getCpf());
            objeto.setEmail(dto.getEmail());

            return barbeiroRepository.save(objeto);
        }catch (Exception e) {
            if (e instanceof DefaultExceptionHandler) {
                throw e;
            } else {
                throw new DefaultExceptionHandler(e);
            }
        }
    }

    public ResponseEntity excluir(Long id) throws DefaultExceptionHandler{
        try{
             barbeiroRepository.deleteById(id);
             return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
