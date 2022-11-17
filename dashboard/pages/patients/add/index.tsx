import React, {useEffect, useState} from 'react';
import PatientService from "@service/patient.service";
import {Patient} from "@model/patient.model";
import {useRouter} from "next/router";
import {TextField} from "@mui/material";
import CustomButton from "../../../components/button/CustomButton";

const PatientPage = () => {

    const router = useRouter();
    const [patient] = useState<Patient>(new Patient({ id: undefined, dob: undefined, address: undefined, family: undefined, sex: undefined, given: undefined, phone: undefined}));

    useEffect(() => {
    }, []);

    const savePatient = () => {
        PatientService.savePatient(patient)
            .then(patient => router.push(`/patients/${patient.data.id}`));
    }

    return (
        <div className={"bg-neutral-700 h-screen w-screen text-white p-12 flex flex-col gap-5"}>
            <h1>New Patient</h1>
            {Object.keys(patient)
                .filter(key => key !== "id")
                .map(key =>
                <>
                    <TextField InputLabelProps={{style : {color : 'white'} }}
                               InputProps={{style: {color: 'white'}}}
                               label={key}
                               defaultValue={patient[key as keyof Patient]}
                               onChange={(e) => patient[key as keyof Patient] = e.target.value}
                    >
                    </TextField>
                </>
            )}
            <CustomButton label={"Save"} onClick={() => savePatient()}/>
            <CustomButton label={"Cancel"} onClick={() => savePatient()}/>
        </div>
    );
};

export default PatientPage;
