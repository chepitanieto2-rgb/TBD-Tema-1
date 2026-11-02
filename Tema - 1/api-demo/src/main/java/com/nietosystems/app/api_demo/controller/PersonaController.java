package com.nietosystems.app.api_demo.controller;

import com.nietosystems.app.api_demo.entity.Persona;
import com.nietosystems.app.api_demo.repository.PersonaRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final PersonaRepository repository;

    public PersonaController(PersonaRepository repository) {
        this.repository = repository;
    }

    // Consultar todas las personas
    @GetMapping
    public List<Persona> listar() {
        return repository.findAll();
    }

    // Registrar una persona
    @PostMapping
    public Persona guardar(@RequestBody Persona persona) {
        persona.setId(null);
        return repository.save(persona);
    }

    // Modificar una persona
    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizar(
            @PathVariable("id") Long id,
            @RequestBody Persona datos) {

        return repository.findById(id)
                .map(persona -> {
                    persona.setNombre(datos.getNombre());
                    persona.setApellidos(datos.getApellidos());
                    persona.setDireccion(datos.getDireccion());
                    persona.setTelefono(datos.getTelefono());

                    return ResponseEntity.ok(
                            repository.save(persona));
                })
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    // Eliminar una persona
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable("id") Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
