package fr.axzial.patientnotes.service;

import fr.axzial.dto.CreatePatientDTO;
import fr.axzial.dto.EditPatientDTO;
import fr.axzial.dto.PatientDTO;
import fr.axzial.model.Patient;
import fr.axzial.patientnotes.mapper.CreatePatientMapper;
import fr.axzial.patientnotes.mapper.EditPatientMapper;
import fr.axzial.patientnotes.mapper.PatientMapper;
import fr.axzial.patientnotes.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final CreatePatientMapper createPatientMapper;
    private final EditPatientMapper editPatientMapper;

    @Override
    public PatientDTO savePatient(@NotNull CreatePatientDTO createPatientDTO) {
        var newPatient = createPatientMapper.toModel(createPatientDTO);
        return map(patientRepository.save(newPatient));
    }

    @Override
    public PatientDTO editPatient(long patientId, EditPatientDTO editPatientDTO) {
        var edited = editPatientMapper.toModel(editPatientDTO);
        edited.setId(patientId);
        return map(patientRepository.save(edited));
    }

    @Override
    public Page<PatientDTO> getPatients() {
        return patientRepository.findAll(PageRequest.of(0, 20)).map(this::map);
    }

    @Override
    public PatientDTO getPatient(long patientId) {
        return map(patientRepository.findById(patientId).orElseThrow());
    }

    public PatientDTO map(Patient patient) {
        return patientMapper.toDTO(patient);
    }

}
