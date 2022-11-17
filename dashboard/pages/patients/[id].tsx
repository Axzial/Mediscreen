import React, {useEffect, useState} from 'react';
import PatientService from "@service/patient.service";
import {Patient} from "@model/patient.model";
import {useRouter} from "next/router";
import CustomButton from "../../components/button/CustomButton";

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

    return (
        <div className={"bg-neutral-700"}>
            <div className={"h-screen w-1/2 text-white p-12 flex flex-col gap-5"}>
                <h1>Patient {id}</h1>
                {Object.keys(patient).map(key =>
                    <p>{key}: {patient[`${key as keyof Patient}`]}</p>
                )}
                <CustomButton label={"Back"} href={`/patients`}/>
                <CustomButton label={"Edit"} href={`/patients/edit/${patient.id}`}/>
                <CustomButton label={"Notes"} href={`/patients/notes/${patient.id}`}/>
                <CustomButton label={"Diabetes"} href={`/patients/diabetes/${patient.id}`}/>
            </div>
        </div>
    );
};

export default PatientPage;
