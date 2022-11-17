import React, {useEffect, useState} from 'react';
import PatientService from "@service/patient.service";
import {List} from "immutable";
import {Patient} from "@model/patient.model";
import {Avatar} from "@mui/material";
import CustomButton from "../../components/button/CustomButton";

const PatientsPage = () => {

    const [patients, setPatients] = useState<List<Patient>>();

    useEffect(() => {
        PatientService.getPatients().then(res => {
            setPatients(List.of(...res.data.content))
        });
    }, []);

    return (
        <div className={"bg-neutral-700 h-screen w-screen text-white p-12 flex flex-col gap-5"}>
            <h1>Patients</h1>
            <h3>Size : {patients?.size}</h3>
            <CustomButton label={"Ajouter un patient"} href={"/patients/add"}/>
            <div className={"grid grid-cols-5"}>
                {patients?.map(patient =>
                    <div className={"flex flex-col bg-neutral-600 rounded-md p-8 w-72 justify-center place-items-center gap-5"}>
                        <Avatar/>
                        <div>
                            {Object.keys(patient).map(key =>
                                <p>{key} {patient[`${key as keyof Patient}`] ?? "null"}</p>
                            )}
                        </div>
                        <CustomButton label={"Show"} href={`/patients/${patient.id}`}/>
                        <CustomButton label={"Edit"} href={`/patients/edit/${patient.id}`}/>
                        <CustomButton label={"Notes"} href={`/patients/notes/${patient.id}`}/>
                        <CustomButton label={"Diabetes"} href={`/patients/diabetes/${patient.id}`}/>
                    </div>
                )}
            </div>
        </div>
    );
};

export default PatientsPage;
