package fr.axzial.diabetesdetection.service;

import fr.axzial.dto.PatientNoteDTO;

import java.util.List;

public interface PatientNoteService {
    List<PatientNoteDTO> getAllPatientNotes(long patientId);
}
