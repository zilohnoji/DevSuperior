package com.devsuperior.demo.controllers;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.dto.EventInsertDTO;
import com.devsuperior.demo.services.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_ADMIN')")
    public ResponseEntity<EventDTO> insert(@Valid @RequestBody EventInsertDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.insert(dto));
    }

    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(eventService.findAll(pageable));
    }
}