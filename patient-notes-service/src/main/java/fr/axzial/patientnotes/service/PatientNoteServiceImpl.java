package fr.axzial.patientnotes.service;

import fr.axzial.dto.CreatePatientNoteDTO;
import fr.axzial.dto.EditPatientNoteDTO;
import fr.axzial.dto.PatientNoteDTO;
import fr.axzial.model.PatientNote;
import fr.axzial.patientnotes.mapper.CreatePatientNoteMapper;
import fr.axzial.patientnotes.mapper.EditPatientNoteMapper;
import fr.axzial.patientnotes.mapper.PatientNoteMapper;
import fr.axzial.patientnotes.repository.PatientNoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PatientNoteServiceImpl implements PatientNoteService {

    private final PatientNoteRepository PatientNoteNoteRepository;
    private final CreatePatientNoteMapper createPatientNoteMapper;
    private final EditPatientNoteMapper editPatientNoteMapper;
    private final PatientNoteMapper patientNoteMapper;

    @Override
    public PatientNoteDTO savePatientNote(String patientId, @NotNull CreatePatientNoteDTO createPatientNoteDTO) {
        PatientNote newPatientNote = createPatientNoteMapper.toModel(createPatientNoteDTO);
        newPatientNote.setPatientId(patientId);
        return map(PatientNoteNoteRepository.save(newPatientNote));
    }

    @Override
    public PatientNoteDTO editPatientNote(String patientId, String id, EditPatientNoteDTO editPatientNoteDTO) {
        PatientNote edited = editPatientNoteMapper.toModel(editPatientNoteDTO);
        edited.setId(id);
        edited.setPatientId(patientId);
        return map(PatientNoteNoteRepository.save(edited));
    }

    @Override
    public List<PatientNote> getPatientNotes(String patientId) {
        return PatientNoteNoteRepository.findAllByPatientId(patientId);
    }

    public PatientNoteDTO map(PatientNote patientNote) {
        return patientNoteMapper.toDTO(patientNote);
    }

}
