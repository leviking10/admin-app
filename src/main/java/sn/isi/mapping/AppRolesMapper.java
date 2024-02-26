package sn.isi.mapping;

import org.mapstruct.Mapper;
import sn.isi.dto.AppRolesDto;
import sn.isi.entities.AppRolesEntity;

@Mapper(componentModel = "spring")
public interface AppRolesMapper {
    AppRolesDto entityToDto(AppRolesEntity appRolesEntity);
    AppRolesEntity dtoToEntity(AppRolesDto appRolesDto);

    default AppRolesEntity fromId(Integer id){
        if(id == null){
            return null;
        }
        AppRolesEntity appRolesEntity =new AppRolesEntity();
        appRolesEntity.setId(id);
        return appRolesEntity;
    }
}
