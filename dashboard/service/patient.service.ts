import axios, {AxiosResponse} from "axios";
import {Patient} from "@model/patient.model";
import {AppProperties} from "@config/AppProperties";
import PageDTO from "../utils/PageDTO";

class PatientService {

    getPatients(): Promise<AxiosResponse<PageDTO<Patient>>> {
        return axios.get<PageDTO<Patient>>(`${AppProperties.PATIENTS_URI}/patients`);
    }

    getPatientById(id: string): Promise<AxiosResponse<Patient>> {
        return axios.get<Patient>(`${AppProperties.PATIENTS_URI}/patients/${id}`);
    }

    editPatient(id: string, data: Partial<Patient>): Promise<AxiosResponse<Patient>> {
        return axios.patch<Patient>(`${AppProperties.PATIENTS_URI}/patients/${id}`, data);
    }

    savePatient(data: Partial<Patient>): Promise<AxiosResponse<Patient>> {
        return axios.post<Patient>(`${AppProperties.PATIENTS_URI}/patients`, data);
    }


}

export default new PatientService();
