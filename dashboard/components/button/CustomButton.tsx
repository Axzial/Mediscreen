import React from 'react';
import {useRouter} from "next/router";

const CustomButton = (props: { label: string, onClick?: () => void, href?: string }) => {

    const router = useRouter();

    const action = () => {
        if (props.onClick) props.onClick()
        if (props.href) router.push(props.href)
    }

    return (
        <button className={"bg-neutral-400 px-6 py-3 rounded-md w-full hover:bg-neutral-500"} onClick={action}>
            <p>{props.label}</p>
        </button>
    );
};

export default CustomButton;
