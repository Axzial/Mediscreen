package fr.axzial.diabetesdetection.service;

import fr.axzial.dto.PatientDTO;
import fr.axzial.model.PatientNote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static fr.axzial.dto.DiabetesDiagnosticStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DiabetesDetectionServiceImplTest {

    @Autowired
    private DiabetesDetectionService diabetesDetectionService;

    List<PatientNote> NOTES = List.of(
            PatientNote.builder().content("Patient has Microalbumine").build(),
            PatientNote.builder().content("Patient has Microalbumine").build(),
            PatientNote.builder().content("Patient is Anormal with Vertige too").build(),
            PatientNote.builder().content("Vertige detected").build()
    );

    @Test
    void detect() {
        assertEquals(diabetesDetectionService.detect(2).getStatus(), DANGER);
    }

    @Test
    void testStatusEarlyOnset() {
        List<PatientNote> notes = List.of(
                PatientNote.builder().content("Patient has Microalbumine").build(),
                PatientNote.builder().content("Patient has Microalbumine").build(),
                PatientNote.builder().content("Patient is Anormal with Vertige too").build(),
                PatientNote.builder().content("Vertige detected").build()
        );

        var patientDTO = PatientDTO.builder()
                .sex("M")
                .dob("2000-12-12")
                .build();

        var diabetesStatus = diabetesDetectionService.getDiabetesStatus(diabetesDetectionService.findKeywords(notes), patientDTO);
        assertEquals(diabetesStatus, EARLY_ONSET);
    }

    @Test
    void testStatusDanger() {
        List<PatientNote> notes = List.of(
                PatientNote.builder().content("Patient has Microalbumine").build(),
                PatientNote.builder().content("Patient has Microalbumine").build(),
                PatientNote.builder().content("Patient is Anormal with Vertige too").build(),
                PatientNote.builder().content("Patient is Anormal with Vertige too").build(),
                PatientNote.builder().content("Vertige detected").build()
        );

        var patientDTO = PatientDTO.builder()
                .sex("M")
                .dob("1970-12-12")
                .build();

        var diabetesStatus = diabetesDetectionService.getDiabetesStatus(diabetesDetectionService.findKeywords(notes), patientDTO);
        assertEquals(diabetesStatus, DANGER);
    }

    @Test
    void testStatusBorderline() {
        List<PatientNote> notes = List.of(
                PatientNote.builder().content("Patient has Microalbumine").build(),
                PatientNote.builder().content("Vertige detected").build()
        );

        var patientDTO = PatientDTO.builder()
                .sex("M")
                .dob("2000-12-12")
                .build();

        var diabetesStatus = diabetesDetectionService.getDiabetesStatus(diabetesDetectionService.findKeywords(notes), patientDTO);
        assertEquals(diabetesStatus, BORDERLINE);
    }

    @Test
    void testStatusNone() {
        List<PatientNote> notes = List.of(
                PatientNote.builder().content("Patient has Microalbumine").build()
        );

        var patientDTO = PatientDTO.builder()
                .sex("M")
                .dob("2000-12-12")
                .build();

        var diabetesStatus = diabetesDetectionService.getDiabetesStatus(diabetesDetectionService.findKeywords(notes), patientDTO);
        assertEquals(diabetesStatus, NONE);
    }

    @Test
    void getPatientAge() {
        var patientDTO = PatientDTO.builder()
                .dob("2000-12-12")
                .build();
        assertEquals(diabetesDetectionService.getPatientAge(patientDTO), 21);
    }

    @Test
    void findKeywords() {
        var keywords = diabetesDetectionService.findKeywords(NOTES);
        assertEquals(keywords, 5);
    }

    @Test
    void findKeywordsColate() {
        var keywords = diabetesDetectionService.findKeywords(List.of(PatientNote.builder().content("Fumeur Fumeur").build()));
        assertEquals(keywords, 2);
    }
}
