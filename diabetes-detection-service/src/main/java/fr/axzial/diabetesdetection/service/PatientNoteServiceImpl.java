package fr.axzial.diabetesdetection.service;

import fr.axzial.dto.PatientNoteDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Transactional
public class PatientNoteServiceImpl implements PatientNoteService {

    private final WebClient webClient;
    private final PatientService patientService;

    public PatientNoteServiceImpl(
            @Value("${patient-note.service.url:http://patient-note-service:8070}") String PATIENT_NOTE_SERVICE_URI,
            PatientService patientService
    ) {
        webClient = WebClient.builder()
                .baseUrl(PATIENT_NOTE_SERVICE_URI)
                .build();
        this.patientService = patientService;
    }

    @Override
    public List<PatientNoteDTO> getAllPatientNotes(long patientId) {
        var patient = patientService.getPatient(patientId);
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/patients-notes/{patientId}")
                                .build(patientId))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PatientNoteDTO>>() {
                })
                .block();
    }

}
