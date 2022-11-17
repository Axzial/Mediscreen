package fr.axzial.diabetesdetection.service;

import fr.axzial.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final WebClient webClient;

    public PatientServiceImpl(
            @Value("${patient.service.url:http://localhost:8090}") String PATIENT_SERVICE_URI
    ) {
        webClient = WebClient.builder()
                .baseUrl(PATIENT_SERVICE_URI)
                .build();
    }

    @Override
    public PatientDTO getPatient(long patientId) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/patients/{id}")
                                .build(patientId))
                .retrieve()
                .bodyToMono(PatientDTO.class)
                .block();
    }

}
