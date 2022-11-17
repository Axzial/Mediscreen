package fr.axzial.patientnotes.mapper;

import fr.axzial.dto.EditPatientDTO;
import fr.axzial.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EditPatientMapper extends IMapper<Patient, EditPatientDTO> {
}
