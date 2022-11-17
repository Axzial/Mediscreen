package fr.axzial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private String id;

    private String family;

    private String given;

    private String dob;

    private String sex;

    private String address;

    private String phone;

}
