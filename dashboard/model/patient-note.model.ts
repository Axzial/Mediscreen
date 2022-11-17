export class PatientNote {

    id?: string;

    content?: string;

    constructor(data?: Partial<PatientNote>) {
        Object.assign(this, data);
    }

}
