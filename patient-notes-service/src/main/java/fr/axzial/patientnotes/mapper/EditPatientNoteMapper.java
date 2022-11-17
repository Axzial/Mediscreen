package fr.axzial.patientnotes.mapper;

import fr.axzial.dto.EditPatientNoteDTO;
import fr.axzial.model.PatientNote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EditPatientNoteMapper extends IMapper<PatientNote, EditPatientNoteDTO> {
}
