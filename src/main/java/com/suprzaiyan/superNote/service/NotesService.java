package com.suprzaiyan.superNote.service;

import com.google.common.base.Enums;
import com.suprzaiyan.superNote.domain.Notes;
import com.suprzaiyan.superNote.domain.enums.NoteStatus;
import com.suprzaiyan.superNote.repository.NotesRepository;
import com.suprzaiyan.superNote.web.error.CustomDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * NotesService - a service class
 *
 * @author vignesh
 * @version 04/10/19
 * @created 04/10/19
 */
@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    /**
     * @param query
     *
     * @return
     */
    public List<Notes> getNotes(String query) {
        List<Notes> notes = new ArrayList<>();
        if (query != null && !query.isEmpty())
            notes.addAll(notesRepository.findByTitleContainingOrBodyContaining(query, query));
        else
            notes.addAll(notesRepository.findAll());
        return notes;
    }

    /**
     * @param notes
     */
    public void createNotes(Notes notes) {
        Notes newNote = new Notes();
        newNote.setBody(notes.getBody() != null ? notes.getBody() : "write anything");
        newNote.setTitle(notes.getTitle() != null ? notes.getTitle() : "write anything");
        newNote.setCreatedDate(ZonedDateTime.now());
        newNote.setModifiedDate(ZonedDateTime.now());
        newNote.setNoteStatus(Enums.getIfPresent(NoteStatus.class, String.valueOf(notes.getNoteStatus())).or(NoteStatus.PENDING));
        notesRepository.save(newNote);
    }

    /**
     * @param id
     *
     * @return
     */
    public Notes findById(Long id) {
        Notes notes = notesRepository.findById(id).orElse(null);
        if (notes == null)
            throw new CustomDataException("Not Found");
        return notes;
    }
}
