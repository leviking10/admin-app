package sn.isi.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sn.isi.dto.AppUserDto;
import sn.isi.entities.AppUserEntity;

@Mapper(componentModel = "spring", uses = {AppRolesMapper.class})
public interface AppUserMapper {
    @Mapping(source= "appRoleEntity.id", target = "approleId")
    AppUserDto entityToDto(AppUserEntity appUserEntity);

    @Mapping(source = "approleId", target ="appRoleEntity")
    AppUserEntity dtoToEntity(AppUserDto appUserDto);
}

