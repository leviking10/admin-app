package sn.isi.mapping;

import org.mapstruct.Mapper;
import sn.isi.dto.ProduitDto;
import sn.isi.entities.ProduitEntity;

@Mapper(componentModel = "spring")
public interface ProduitMapper {
    ProduitDto entityToDto(ProduitEntity produitEntity);
    ProduitEntity dtoToEntity(ProduitDto produitDto);
}
