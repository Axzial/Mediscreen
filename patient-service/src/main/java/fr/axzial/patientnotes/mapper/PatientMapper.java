package fr.axzial.patientnotes.mapper;

import fr.axzial.dto.PatientDTO;
import fr.axzial.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper extends IMapper<Patient, PatientDTO> {
}
