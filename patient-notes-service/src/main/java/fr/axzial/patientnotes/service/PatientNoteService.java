package fr.axzial.patientnotes.service;

import fr.axzial.dto.CreatePatientNoteDTO;
import fr.axzial.dto.EditPatientNoteDTO;
import fr.axzial.dto.PatientNoteDTO;
import fr.axzial.model.PatientNote;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface PatientNoteService {
    PatientNoteDTO savePatientNote(String patientId, @NotNull CreatePatientNoteDTO createPatientNoteDTO);

    List<PatientNote> getPatientNotes(String patientId);

    PatientNoteDTO editPatientNote(String patientId, String id, EditPatientNoteDTO editPatientNoteDTO);
}
