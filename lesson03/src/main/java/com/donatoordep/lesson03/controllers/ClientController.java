package com.donatoordep.lesson03.controllers;

import com.donatoordep.lesson03.dto.ClientDTO;
import com.donatoordep.lesson03.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
