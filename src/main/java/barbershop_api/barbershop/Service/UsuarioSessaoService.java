package barbershop_api.barbershop.Service;

import barbershop_api.barbershop.Exceptions.DefaultExceptionHandler;
import barbershop_api.barbershop.Model.BarbeiroEntity;
import barbershop_api.barbershop.Model.UsuarioSessaoEntity;
import barbershop_api.barbershop.Repository.UsuarioSessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UsuarioSessaoService {

    @Autowired
    private UsuarioSessaoRepository repository;

    public void registrarLogin(BarbeiroEntity dto, Date dataValidadeToken) {

        this.finalizarSessoesExpiradas();

        UsuarioSessaoEntity novaSessao = new UsuarioSessaoEntity();
        novaSessao.setCodUsuario(dto.getId());
        novaSessao.setNomUsuario(dto.getNome());
        novaSessao.setStatus("O");
        novaSessao.setDataLogin(new Date());
        novaSessao.setDataValidadeToken(dataValidadeToken);
        repository.save(novaSessao);
    }

    public void finalizarSessoesExpiradas(){
        Date atual = new Date();

        List<UsuarioSessaoEntity> sessoesExpiradas = repository.findByStatusAndDataValidadeTokenBefore("O", atual);

        if(!sessoesExpiradas.isEmpty()){
            for(UsuarioSessaoEntity sessao : sessoesExpiradas){
                if(sessao.getDataLogout() == null){
                    sessao.setDataLogout(sessao.getDataValidadeToken());
                }
                sessao.setStatus("F");
            }
            repository.saveAll(sessoesExpiradas);
        }
    }

    @Transactional
    public ResponseEntity registrarLogout(Long codUsuario) throws DefaultExceptionHandler{
        Pageable limit = PageRequest.of(0, 1);
        List<UsuarioSessaoEntity> sessoes = repository.findBySessaoAtivaMaisRecente(codUsuario, "O", limit);

        if(!sessoes.isEmpty()){
            UsuarioSessaoEntity sesssoesAtivas = sessoes.get(0);
            sesssoesAtivas.setDataLogout(new Date());
            sesssoesAtivas.setStatus("F");
            repository.save(sesssoesAtivas);
        }
        return ResponseEntity.ok("Logout realizado com sucesso.");
    }


}
