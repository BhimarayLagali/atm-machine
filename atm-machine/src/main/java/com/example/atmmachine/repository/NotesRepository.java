package com.example.atmmachine.repository;

import com.example.atmmachine.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Note, Long> {
    Note findByDenomination(String denomination);
}

