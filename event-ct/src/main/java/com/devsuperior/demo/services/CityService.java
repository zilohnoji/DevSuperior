package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.exceptions.ONBEntityNotFoundException;
import com.devsuperior.demo.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public CityDTO insert(CityDTO dto) {
        City entity = new City(dto.getId(), dto.getName());
        return new CityDTO(cityRepository.save(entity));
    }

    public List<CityDTO> findAll() {
        return cityRepository.findAll(Sort.by("name")).stream().map(CityDTO::new).toList();
    }

    public void delete(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new ONBEntityNotFoundException();
        }

        try {
            cityRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("data integrity violation");
        }
    }
}
