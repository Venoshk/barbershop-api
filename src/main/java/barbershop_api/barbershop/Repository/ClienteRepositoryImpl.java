//package barbershop_api.barbershop.Repository;
//
//import barbershop_api.barbershop.DTO.ClienteDTO;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.Query;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class ClienteRepositoryImpl {
//    @Autowired
//    private EntityManager entityManager;
//
//    public List<ClienteDTO> listarCliente(){
//        String sql = getSqlQuery();
//
//        Query query = entityManager.createNativeQuery(sql, "ClientesDTOMapping");
//        return query.getResultList();
//    }
//
//    public String getSqlQuery(){
//        return """
//
//               """
//    }
//}
