package barbershop_api.barbershop.Service;

import barbershop_api.barbershop.DTO.CortesDTO;
import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.CortesEntity;
import barbershop_api.barbershop.Repository.CortesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static barbershop_api.barbershop.ModelMapper.CortesMapper.*;

import java.util.List;

@Service
public class CortesService {

    @Autowired
    private CortesRepository cortesRepository;



    public List<CortesEntity> listar() throws DefaultExceptionHandler{
        try{
            return cortesRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public CortesDTO incluir(CortesDTO dto) throws DefaultExceptionHandler {
        try{
          CortesEntity entity = toEntity(dto);

          CortesEntity salvo = cortesRepository.save(entity);
          return toDTO(salvo);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity deletar(Long id) throws DefaultExceptionHandler {
        try {
            cortesRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            if (e instanceof DefaultExceptionHandler) {
                throw e;
            } else {
                throw new DefaultExceptionHandler(e);
            }
        }
    }

    public CortesEntity buscarPorId(Long id) throws DefaultExceptionHandler {
        try{
            return cortesRepository.findById(id)
                    .orElseThrow(()-> new DefaultExceptionHandler(HttpStatus.BAD_REQUEST.value(),
                            "Operação inválida! Não existe nenhuma informação para este ID."));
        } catch (Exception e) {
            if (e instanceof DefaultExceptionHandler) {
                throw e;
            } else {
                throw new DefaultExceptionHandler(e);
            }
        }
    }
}
