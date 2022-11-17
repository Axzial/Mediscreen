package fr.axzial.dto;

import fr.axzial.model.SexType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientDTO {

    private String family;

    private String given;

    private String dob;

    private SexType sex;

    private String address;

    private String phone;

}
