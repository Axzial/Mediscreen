import axios, {AxiosResponse} from "axios";
import {DiabetesDiagnosticDTO} from "@model/diabetes-diagnostic.model";
import {AppProperties} from "@config/AppProperties";

class DiabetesDetectionService {

    detectDiabetes(patientId: string): Promise<AxiosResponse<DiabetesDiagnosticDTO>> {
        return axios.get<DiabetesDiagnosticDTO>(`${AppProperties.DIABETES_DIAGNOSTIC_URI}/${patientId}`)
    }

}

export default new DiabetesDetectionService();
