import { useEffect, useRef, useState } from "react";
import './creationCompte.css';


const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const otherRegex = /^[a-zA-ZÀ-ÿ\- ]{2,}$/;
const msgRegex = /^[a-zA-Z0-9À-ÿ\- ]{20,350}$/;

export function ChampSaisie({setValue, label}) {

    const [errMsg, setErrMsg] = useState([{}]);

    const [input, setInput] = useState('');
    const [validInput, setValidInput] = useState(false);
    const [focusInput, setFocusInput] = useState(false);


    const inputRef = useRef();

    useEffect(() => {
        inputRef.current.focus()
    }, [])

    useEffect(() => {
    const result = otherRegex.test(input)
    setValidInput(result)
    }, [input])

    const handleChange = () => {
        setInput(inputRef.current.value)
    }


    const addMsgError = (msgParam, focusSeter, validElement) => {
        focusSeter(false)
        const newMsg = validElement ? "" : msgParam;
        setErrMsg(newMsg)
    }

    return (
        <div className='row g-2 mb-3'>
                <div className="col-md">
                    <label htmlFor="nom" className="form-label">{label}</label>
                    <div className="champ">
                        
                        <input
                            style={{ backgroundColor: 'rgba(255, 255, 255, 0.5)', color: 'black', border: 'none'}}
                            ref={inputRef}
                            onChange={handleChange}
                            onBlur={
                                () => addMsgError('Votre nom doit être au bon format!', setFocusInput, validInput)
                            }
                            onFocus={() => { setFocusInput(true) }}
                            type="text"
                            //   className={validName ? "is-valid form-control" : "is-invalid form-control"} 
                            className={
                                !input ? "form-control" : validInput ? "is-valid form-control" : "is-invalid form-control"
                            }
                            // imbrique les turners pour les conditions d'affichage du champ de saisie
                            disabled={false}
                            id="input"
                            aria-describedby="emailHelp"
                        />

                    </div>
                    {
                        (!validInput && input && !focusInput) && <div className="alert alert-danger mt-3" role="alert">
                            {errMsg}
                        </div>
                    }
                </div>
              </div>
    )
}