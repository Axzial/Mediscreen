package fr.axzial.patientnotes.controller;

import fr.axzial.dto.CreatePatientDTO;
import fr.axzial.dto.EditPatientDTO;
import fr.axzial.dto.PatientDTO;
import fr.axzial.patientnotes.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public PatientDTO savePatient(@Valid @RequestBody CreatePatientDTO createPatientDTO) {
        return patientService.savePatient(createPatientDTO);
    }

    @PatchMapping("/{id}")
    public PatientDTO editPatient(@PathVariable long id, @Valid @RequestBody EditPatientDTO editPatientDTO) {
        return patientService.editPatient(id, editPatientDTO);
    }

    @GetMapping
    public Page<PatientDTO> getAllPatients() {
        return patientService.getPatients();
    }

    @GetMapping("/{id}")
    public PatientDTO getAllPatients(@PathVariable long id) {
        return patientService.getPatient(id);
    }

}
