package fr.axzial.patientnotes.mapper;

import fr.axzial.dto.PatientNoteDTO;
import fr.axzial.model.PatientNote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientNoteMapper extends IMapper<PatientNote, PatientNoteDTO> {
}
