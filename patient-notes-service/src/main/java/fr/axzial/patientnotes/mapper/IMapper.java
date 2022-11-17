package fr.axzial.patientnotes.mapper;

public interface IMapper<MODEL, DTO> {
    DTO toDTO(MODEL model);
    MODEL toModel(DTO dto);
}
