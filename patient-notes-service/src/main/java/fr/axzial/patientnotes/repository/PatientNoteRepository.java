package fr.axzial.patientnotes.repository;

import fr.axzial.model.PatientNote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientNoteRepository extends CrudRepository<PatientNote, String> {

    List<PatientNote> findAllByPatientId(String patientId);
}
