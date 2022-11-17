package fr.axzial.patientnotes.mapper;

import fr.axzial.dto.CreatePatientDTO;
import fr.axzial.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreatePatientMapper extends IMapper<Patient, CreatePatientDTO> {
}
