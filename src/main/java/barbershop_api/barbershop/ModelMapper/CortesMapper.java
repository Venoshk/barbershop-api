package barbershop_api.barbershop.ModelMapper;

import barbershop_api.barbershop.DTO.CortesDTO;
import barbershop_api.barbershop.Model.CortesEntity;

public class CortesMapper {

    public static CortesDTO toDTO(CortesEntity entity) {
        if (entity == null) return null;

        return CortesDTO.builder()
                .id(entity.getId())
                .preco(entity.getPreco())
                .desc(entity.getDesc())
                .categoria(entity.getCategoria())
                .ativo(entity.getAtivo())
                .dataCriacao(entity.getDataCriacao())
                .dataAtualizacao(entity.getDataAtualizacao())
                .build();
    }

    public static CortesEntity toEntity(CortesDTO dto) {
        if (dto == null) return null;

        return CortesEntity.builder()
                .id(dto.getId())
                .preco(dto.getPreco())
                .desc(dto.getDesc())
                .categoria(dto.getCategoria())
                .ativo(dto.getAtivo())
                .dataCriacao(dto.getDataCriacao())
                .dataAtualizacao(dto.getDataAtualizacao())
                .build();
    }
}
