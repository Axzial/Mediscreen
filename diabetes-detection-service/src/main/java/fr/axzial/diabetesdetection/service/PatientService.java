package fr.axzial.diabetesdetection.service;

import fr.axzial.dto.PatientDTO;

public interface PatientService {
    PatientDTO getPatient(long patientId);
}
