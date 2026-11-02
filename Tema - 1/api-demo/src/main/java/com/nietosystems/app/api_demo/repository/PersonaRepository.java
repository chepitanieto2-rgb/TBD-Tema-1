package com.nietosystems.app.api_demo.repository;

import com.nietosystems.app.api_demo.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository
        extends JpaRepository<Persona, Long> {
}