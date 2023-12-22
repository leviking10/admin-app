package sn.isi.service;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.doa.IAppUserRepository;
import sn.isi.dto.AppUserDto;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import sn.isi.mapping.AppUserMapper;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppUserService {
    private final IAppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final MessageSource messageSource;

    public AppUserService(IAppUserRepository appUserRepository, AppUserMapper appUserMapper, MessageSource messageSource) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<AppUserDto> getAllUsers() {
        return StreamSupport.stream(appUserRepository.findAll().spliterator(), false)
            .map(appUserMapper::entityToDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AppUserDto getUser(int id) {
        return appUserMapper.entityToDto(appUserRepository.findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                    Locale.getDefault()))));
    }

    @Transactional
    public AppUserDto createUser(AppUserDto appUserDto) {
        return appUserMapper.entityToDto(appUserRepository.save(appUserMapper.dtoToEntity(appUserDto)));
    }

    @Transactional
    public AppUserDto updateUser(int id, AppUserDto appUserDto) {
        return appUserRepository.findById(id)
            .map(entity -> {
                appUserDto.setId(id);
                return appUserMapper.entityToDto(
                    appUserRepository.save(appUserMapper.dtoToEntity(appUserDto)));
            }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                Locale.getDefault())));
    }

    @Transactional
    public void deleteUser(int id) {
        try {
            appUserRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("user.errordeletion", new Object[]{id},
                Locale.getDefault()),
                HttpStatus.CONFLICT);
        }
    }
}
