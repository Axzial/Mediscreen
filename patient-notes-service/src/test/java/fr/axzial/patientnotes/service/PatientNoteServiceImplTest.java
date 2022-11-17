package fr.axzial.patientnotes.service;

import fr.axzial.dto.CreatePatientNoteDTO;
import fr.axzial.dto.EditPatientNoteDTO;
import fr.axzial.model.PatientNote;
import fr.axzial.patientnotes.repository.PatientNoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class PatientNoteServiceImplTest {

    @Autowired
    PatientNoteRepository patientNoteRepository;
    @Autowired
    PatientNoteService patientNoteService;

    String PATIENT_ID = "82";

    PatientNote patientNote = PatientNote.builder()
            .patientId(PATIENT_ID)
            .content(":)")
            .build();

    @BeforeEach
    void setUp() {
        patientNoteRepository.deleteAll();
    }

    @Test
    void savePatientNote() {
        var patientNoteDTO = CreatePatientNoteDTO.builder().content("Hey").build();
        patientNoteService.savePatientNote(PATIENT_ID, patientNoteDTO);
        assertEquals(patientNoteRepository.findAllByPatientId(PATIENT_ID).size(), 1);
    }

    @Test
    void editPatientNote() {
        var edited = patientNoteRepository.save(patientNote);
        var coolContent = EditPatientNoteDTO.builder().content("Cool Content").build();
        patientNoteService.editPatientNote(PATIENT_ID, edited.getId(), coolContent);
        assertEquals(patientNoteRepository.findById(edited.getId()).orElseThrow().getContent(), "Cool Content");
    }

    @Test
    void getPatientNotes() {
        patientNoteRepository.save(patientNote);
        assertEquals(patientNoteService.getPatientNotes(PATIENT_ID).size(), 1);
    }

}
