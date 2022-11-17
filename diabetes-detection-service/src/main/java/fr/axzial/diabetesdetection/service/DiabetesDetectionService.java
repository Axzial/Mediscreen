package fr.axzial.diabetesdetection.service;

import fr.axzial.dto.DiabetesDiagnosticDTO;
import fr.axzial.dto.DiabetesDiagnosticStatus;
import fr.axzial.dto.PatientDTO;
import fr.axzial.model.PatientNote;
import lombok.SneakyThrows;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface DiabetesDetectionService {

    DiabetesDiagnosticDTO detect(@NotNull long patientId);

    @SneakyThrows
    int getPatientAge(PatientDTO patientDTO);

    DiabetesDiagnosticStatus getDiabetesStatus(int matches, PatientDTO patientDTO);

    int findKeywords(List<PatientNote> patientNotes);
}
