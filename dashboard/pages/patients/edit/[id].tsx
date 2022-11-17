import React, {useEffect, useState} from 'react';
import PatientService from "@service/patient.service";
import {Patient} from "@model/patient.model";
import {useRouter} from "next/router";
import {TextField} from "@mui/material";
import CustomButton from "../../../components/button/CustomButton";

const PatientPage = () => {

    const router = useRouter();
    const { id } = router.query;
    const [patient, setPatient] = useState<Patient>();

    useEffect(() => {
        if (id) {
            PatientService.getPatientById(id as string).then(res => {
                setPatient(res.data)
            });
        }
    }, [id]);

    if (!patient) return null;

    const updatePatient = () => {
        PatientService.editPatient(id as string, patient)
            .then(patient => router.push(`/patients/${patient.data.id}`));
    }

    return (
        <div className={"bg-neutral-700 h-screen w-screen text-white p-12 flex flex-col gap-5"}>
            <h1>Patient {id}</h1>
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
            <CustomButton label={"Edit"} onClick={() => updatePatient()}/>
            <CustomButton label={"Cancel"} onClick={() => updatePatient()}/>
        </div>
    );
};

export default PatientPage;
