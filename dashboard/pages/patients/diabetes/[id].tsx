import React, {useEffect, useState} from 'react';
import {useRouter} from "next/router";
import DiabetesDetectionService from "@service/diabetes-detection.service";
import {DiabetesDiagnosticDTO} from "@model/diabetes-diagnostic.model";
import CustomButton from "../../../components/button/CustomButton";
import {Patient} from "@model/patient.model";

const PatientPage = () => {

    const router = useRouter();
    const {id} = router.query;
    const [diabetesDiagnostic, setDiabetesDiagnostic] = useState<DiabetesDiagnosticDTO>();

    useEffect(() => {
        if (id) {
            DiabetesDetectionService.detectDiabetes(id as string).then(res => {
                setDiabetesDiagnostic(res.data)
            });
        }
    }, [id]);

    if (!diabetesDiagnostic) return null;

    const colorStatus = () => {
        switch (diabetesDiagnostic.status) {
            case "NONE": return "white";
            case "BORDERLINE": return "yellow";
            case "DANGER": return "orange";
            case "EARLY_ONSET": return "red";
        }
    }

    if (!diabetesDiagnostic) return null;
    if (!diabetesDiagnostic.patient) return null;

    return (
        <div className={"bg-neutral-700"}>
            <div className={"h-screen w-1/2 text-white p-12 flex flex-col gap-5"}>
                <h1>Patient Diabetes : <span style={{color: colorStatus()}}>{diabetesDiagnostic.status}</span></h1>
                {Object.keys(diabetesDiagnostic.patient).map(key =>
                    /* @ts-ignore */
                    <p>{key}: {diabetesDiagnostic?.patient[`${key as keyof Patient}`]}</p>
                )}
                <CustomButton label={"Back"} href={`/patients/${id}`}/>
            </div>
        </div>
    );
};

export default PatientPage;
