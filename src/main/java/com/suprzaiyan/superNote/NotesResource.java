package com.suprzaiyan.superNote;

import com.suprzaiyan.superNote.domain.Notes;
import com.suprzaiyan.superNote.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * NotesResource - a controller class
 *
 * @author vignesh
 * @version 03/10/19
 * @created 03/10/19
 */
@RestController
@RequestMapping("/api/")
public class NotesResource {

    @Autowired
    private NotesService notesService;

    /**
     * @param query
     *
     * @return
     */
    @GetMapping("notes")
    public ResponseEntity<List<Notes>> getNotes(@RequestParam(required = false) String query) {
        List<Notes> notes = notesService.getNotes(query);
        return notes.isEmpty() ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok().body(notes);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("notes/{id}")
    public ResponseEntity<Notes> getNotes(@PathVariable Long id) {
        Notes notes = notesService.findById(id);
        return notes==null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok().body(notes);
    }

    /**
     *
     * @param notes
     * @return
     */
    @PostMapping("notes")
    public ResponseEntity<Void> createNote(@RequestBody Notes notes){
        try{
            if (notes!=null)
                notesService.createNotes(notes);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){

        }
        return ResponseEntity.badRequest().body(null);
    }
}
