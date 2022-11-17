import React, {useEffect, useState} from 'react';
import {useRouter} from "next/router";
import {PatientNote} from "@model/patient-note.model";
import PatientNoteService from "@service/patient-note.service";
import {List} from "immutable";
import CustomButton from "../../../components/button/CustomButton";
import {TextField} from "@mui/material";

const PatientNotesPage = () => {

    const router = useRouter();
    const {id} = router.query;
    const [patientNotes, setPatientNotes] = useState<List<PatientNote>>();
    const [newNoteContent, setNewNoteContent] = useState<string>();

    useEffect(() => {
        if (id) {
            PatientNoteService.getPatientNotes(id as string).then(res => {
                setPatientNotes(List.of(...res.data))
            });
        }
    }, [id]);

    const saveNote = () => {
        if (newNoteContent) {
            PatientNoteService.savePatientNote(id as string, newNoteContent)
                .then(res => {
                    setPatientNotes(patientNotes?.push(res.data))
                    setNewNoteContent('')
                })
        }
    }

    if (!patientNotes) return null;

    return (
        <div className={"bg-neutral-700"}>
            <div className={"h-full w-1/2 text-white p-12 flex flex-col gap-5"}>
                <h1>Patient Notes</h1>
                {patientNotes.map(note =>
                    <div
                        className={"flex flex-col bg-neutral-600 rounded-md p-4 w-[128] justify-center place-items-center gap-5"}>
                        <h1>ID: {note.id}</h1>
                        <p>Content: {note.content}</p>
                    </div>
                )}
                <CustomButton label={"Back"} href={`/patients/${id}`}/>
                <h1>Ajouter une note</h1>
                <TextField
                    InputLabelProps={{style : {color : 'white'} }}
                    InputProps={{style: {color: 'white'}}}
                    multiline={true}
                    rows={10} label={"Note"}
                    onChange={(e) => setNewNoteContent(e.target.value)}
                    placeholder={"Note..."}>
                </TextField>
                <CustomButton label={"Save"} onClick={() => saveNote()}/>
            </div>
        </div>
    );
};

export default PatientNotesPage;
