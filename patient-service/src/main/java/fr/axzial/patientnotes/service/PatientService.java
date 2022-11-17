package fr.axzial.patientnotes.service;

import fr.axzial.dto.CreatePatientDTO;
import fr.axzial.dto.EditPatientDTO;
import fr.axzial.dto.PatientDTO;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;

public interface PatientService {
    PatientDTO savePatient(@NotNull CreatePatientDTO createPatientDTO);

    Page<PatientDTO> getPatients();

    PatientDTO editPatient(long patientId, EditPatientDTO editPatientDTO);

    PatientDTO getPatient(long patientId);
}
