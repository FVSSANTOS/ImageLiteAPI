import React from "react";

interface InputTextProps{
    style?: string;
    onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void;
    placeHolder?: string;
    id: string;
}

export const InputText: React.FC<InputTextProps> = ({style, ...rest}: InputTextProps) => {
    return(
        <input type="text" 
               {...rest}
               className={`${style} border-gray-400 border px-3 py-2 rounded-lg text-gray-900`}/>
    )
}