import axios, {AxiosResponse} from "axios";
import {AppProperties} from "@config/AppProperties";
import {PatientNote} from "@model/patient-note.model";

class PatientNoteService {

    getPatientNotes(patientId: string): Promise<AxiosResponse<PatientNote[]>> {
        return axios.get<PatientNote[]>(`${AppProperties.PATIENTS_NOTES_URI}/${patientId}`);
    }

    savePatientNote(patientId: string, content: string): Promise<AxiosResponse<PatientNote>> {
        return axios.post<PatientNote>(`${AppProperties.PATIENTS_NOTES_URI}/${patientId}`, { content: content })
    }

}

export default new PatientNoteService();
