package fr.axzial.diabetesdetection.controller;

import fr.axzial.diabetesdetection.service.DiabetesDetectionService;
import fr.axzial.dto.DiabetesDiagnosticDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diabetes-detection")
@RequiredArgsConstructor
public class DiabetesDetectionController {

    private final DiabetesDetectionService diabetesDetectionService;

    @GetMapping("/{patientId}")
    public DiabetesDiagnosticDTO detectDiabete(@PathVariable long patientId) {
        return diabetesDetectionService.detect(patientId);
    }

}
