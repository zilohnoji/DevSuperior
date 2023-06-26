package com.donatoordep.lesson03.services;

import com.donatoordep.lesson03.dto.ClientDTO;
import com.donatoordep.lesson03.mappers.ClientMapper;
import com.donatoordep.lesson03.repositories.ClientRepository;
import com.donatoordep.lesson03.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientMapper mapper;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(x -> mapper.toDto(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException();
        } else {
            repository.deleteById(id);
        }
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        if (repository.existsById(id)) {
            dto.setId(id);
            return mapper.toDto(repository.save(mapper.toEntity(dto)));
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
