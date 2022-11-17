export class Patient {

    id?: string;

    family?: string;

    given?: string;

    dob?: string;

    sex?: string;

    address?: string;

    phone?: string;


    constructor(data?: Partial<Patient>) {
        Object.assign(this, data);
    }

}
