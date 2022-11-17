package fr.axzial.patientnotes.controller;

import fr.axzial.dto.CreatePatientNoteDTO;
import fr.axzial.dto.EditPatientNoteDTO;
import fr.axzial.dto.PatientNoteDTO;
import fr.axzial.model.PatientNote;
import fr.axzial.patientnotes.service.PatientNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patients-notes/{patientId}")
@RequiredArgsConstructor
public class PatientNoteController {

    private final PatientNoteService patientNoteService;

    @PostMapping
    public PatientNoteDTO savePatientNote(@PathVariable String patientId, @Valid @RequestBody CreatePatientNoteDTO createPatientNoteDTO) {
        return patientNoteService.savePatientNote(patientId, createPatientNoteDTO);
    }

    @PatchMapping("/{id}")
    public PatientNoteDTO editPatientNote(@PathVariable String patientId, @PathVariable String id, @Valid @RequestBody EditPatientNoteDTO editPatientNoteDTO) {
        return patientNoteService.editPatientNote(patientId, id, editPatientNoteDTO);
    }

    @GetMapping
    public List<PatientNote> getAllPatientNotes(@PathVariable String patientId) {
        return patientNoteService.getPatientNotes(patientId);
    }

}
