package fr.axzial.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Data
public class PatientNote {

    @Id
    @Indexed
    String id;

    @Indexed
    String patientId;

    String content;

}
