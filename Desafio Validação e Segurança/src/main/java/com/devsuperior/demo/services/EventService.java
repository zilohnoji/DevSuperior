package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.dto.EventInsertDTO;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final CityRepository cityRepository;

    @Autowired
    public EventService(EventRepository eventRepository, CityRepository cityRepository) {
        this.eventRepository = eventRepository;
        this.cityRepository = cityRepository;
    }


    public EventDTO insert(EventInsertDTO dto) {
        Event entity = new Event(dto.getName(), dto.getDate(), dto.getUrl(), cityRepository.findById(dto.getCityId()).get());
        return new EventDTO(eventRepository.save(entity));
    }

    public Page<EventDTO> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable).map(EventDTO::new);
    }
}
