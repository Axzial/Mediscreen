import {Patient} from "@model/patient.model";

export class DiabetesDiagnosticDTO {

    status?: string;

    patient?: Patient;

    constructor(data?: Partial<DiabetesDiagnosticDTO>) {
        Object.assign(this, data);
    }

}
