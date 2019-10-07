package com.suprzaiyan.superNote.repository;

import com.suprzaiyan.superNote.domain.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NotesRepository - a repo class
 *
 * @author vignesh
 * @version 04/10/19
 * @created 04/10/19
 */
@Repository
public interface NotesRepository extends JpaRepository<Notes,Long> {

    List<Notes> findByTitleContainingOrBodyContaining(String title, String body);
}
