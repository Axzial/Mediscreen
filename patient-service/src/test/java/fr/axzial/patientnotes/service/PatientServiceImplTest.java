package fr.axzial.patientnotes.service;

import fr.axzial.dto.EditPatientDTO;
import fr.axzial.model.Patient;
import fr.axzial.patientnotes.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class PatientServiceImplTest {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientService patientService;

    Patient mockPatient = Patient.builder().build();

    @BeforeEach
    void setUp() {
        patientRepository.deleteAll();
    }

    @Test
    void savePatient() {
        patientRepository.save(mockPatient);
        assertEquals(patientRepository.findAll(Pageable.ofSize(20)).getTotalElements(), 1);
    }

    @Test
    void editPatient() {
        var save = patientRepository.save(mockPatient);
        var editPatientDTO = EditPatientDTO.builder().family("BROWN").build();
        patientService.editPatient(save.getId(), editPatientDTO);
        assertEquals(patientService.getPatient(save.getId()).getFamily(), "BROWN");
    }

    @Test
    void getPatients() {
        patientRepository.save(mockPatient);
        assertEquals(patientService.getPatients().getTotalElements(), 1);
    }

    @Test
    void getPatient() {
        patientRepository.save(mockPatient);
        assertNotNull(patientService.getPatient(mockPatient.getId()));
    }
}
