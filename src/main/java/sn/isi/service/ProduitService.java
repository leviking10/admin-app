package sn.isi.service;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.doa.IProduitRepository;
import sn.isi.dto.ProduitDto;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import sn.isi.mapping.ProduitMapper;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProduitService {
    private final IProduitRepository produitRepository;
    private final ProduitMapper produitMapper;
    private final MessageSource messageSource;

    public ProduitService(IProduitRepository produitRepository, ProduitMapper produitMapper, MessageSource messageSource) {
        this.produitRepository = produitRepository;
        this.produitMapper = produitMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<ProduitDto> getAllProduits() {
        return StreamSupport.stream(produitRepository.findAll().spliterator(), false)
            .map(produitMapper::entityToDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProduitDto getProduit(int id) {
        return produitMapper.entityToDto(produitRepository.findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("produit.notfound", new Object[]{id},
                    Locale.getDefault()))));
    }

    @Transactional
    public ProduitDto createProduit(ProduitDto produitDto) {
        return produitMapper.entityToDto(produitRepository.save(produitMapper.dtoToEntity(produitDto)));
    }

    @Transactional
    public ProduitDto updateProduit(int id, ProduitDto produitDto) {
        return produitRepository.findById(id)
            .map(entity -> {
                produitDto.setId(id);
                return produitMapper.entityToDto(
                    produitRepository.save(produitMapper.dtoToEntity(produitDto)));
            }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("produit.notfound", new Object[]{id},
                Locale.getDefault())));
    }

    @Transactional
    public void deleteProduit(int id) {
        try {
            produitRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("produit.errordeletion", new Object[]{id},
                Locale.getDefault()),
                HttpStatus.CONFLICT);
        }
    }
}
