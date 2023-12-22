package sn.isi.mapping;

import org.mapstruct.Mapper;
import sn.isi.dto.AppUserDto;
import sn.isi.entities.AppUserEntity;

@Mapper(componentModel = "spring", uses = {AppRolesMapper.class})
public interface AppUserMapper {
    AppUserDto entityToDto(AppUserEntity appUserEntity);
    AppUserEntity dtoToEntity(AppUserDto appUserDto);
}

