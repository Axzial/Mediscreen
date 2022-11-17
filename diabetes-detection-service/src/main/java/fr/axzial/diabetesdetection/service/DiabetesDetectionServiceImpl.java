package fr.axzial.diabetesdetection.service;

import fr.axzial.dto.DiabetesDiagnosticDTO;
import fr.axzial.dto.DiabetesDiagnosticStatus;
import fr.axzial.dto.PatientDTO;
import fr.axzial.model.PatientNote;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static fr.axzial.dto.DiabetesDiagnosticStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
public class DiabetesDetectionServiceImpl implements DiabetesDetectionService {

    private final PatientService patientService;
    private final PatientNoteService patientNoteService;

    private final List<String> TRIGGER_WORDS = List.of(
            "Hémoglobine A1C",
            "Microalbumine",
            "Taille",
            "Poids",
            "Fumeur",
            "Anormal",
            "Cholestérol",
            "Vertige"
    );

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class DiabetesStatus {
        int value;
    }

    @Override
    public DiabetesDiagnosticDTO detect(@NotNull long patientId) {
        var patient = patientService.getPatient(patientId);
        var patientNotes = patientNoteService.getAllPatientNotes(patientId);
        var matches = findKeywords(patientNotes.stream().map(note -> PatientNote.builder().content(note.getContent()).build()).collect(Collectors.toList()));

        var status = getDiabetesStatus(matches, patient);

        return DiabetesDiagnosticDTO.builder().patient(patient).status(status).build();
    }

    @Override
    public int getPatientAge(PatientDTO patientDTO) {
        var dob = LocalDate.parse(patientDTO.getDob(), DateTimeFormatter.ISO_LOCAL_DATE);
        var now = LocalDate.now();
        var period = Period.between(dob, now);
        return period.getYears();
    }

    @Override
    public DiabetesDiagnosticStatus getDiabetesStatus(int matches, PatientDTO patientDTO) {
        var status = NONE;

        System.out.println("Evaluate : " + matches);
        if (matches >= 2 && (getPatientAge(patientDTO) <= 30)) {
            status = BORDERLINE;
        }

        if (patientDTO.getSex().equalsIgnoreCase("M")) {
            if (getPatientAge(patientDTO) < 30) {
                if (matches >= 3) status = DANGER;
                if (matches >= 5) status = EARLY_ONSET;
            } else {
                if (matches >= 6) status = DANGER;
                if (matches >= 8) status = EARLY_ONSET;
            }
        } else {
            if (getPatientAge(patientDTO) < 30) {
                if (matches >= 4) status = DANGER;
                if (matches >= 7) status = EARLY_ONSET;
            } else {
                if (matches >= 6) status = DANGER;
                if (matches >= 8) status = EARLY_ONSET;
            }
        }

        return status;
    }

    /**
     * Return Matches Amount
     *
     * @return
     */
    @Override
    public int findKeywords(List<PatientNote> patientNotes) {
        return TRIGGER_WORDS.stream()
                .map(word -> patientNotes.stream()
                        .map(note -> {
                            int count = 0;
                            var content = note.getContent();
                            while (content.contains(word)) {
                                count++;
                                content = content.replaceFirst(word, "");
                            }
                            return count;
                        })
                        .reduce(Integer::sum)
                        .orElse(0))
                .reduce(Integer::sum)
                .orElse(0);
    }


}
