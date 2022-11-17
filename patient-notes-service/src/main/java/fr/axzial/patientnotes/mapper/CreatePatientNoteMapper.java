package fr.axzial.patientnotes.mapper;

import fr.axzial.dto.CreatePatientNoteDTO;
import fr.axzial.model.PatientNote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreatePatientNoteMapper extends IMapper<PatientNote, CreatePatientNoteDTO> {
}
