import Wrapper from '../../Wrapper'
import { useEffect, useState } from "react";
import { ChampSaisie } from './champSaisie';
import './creationCompte.css';
import Container from 'react-bootstrap/esm/Container';
// import Row from 'react-bootstrap/esm/Row';
// import Col from 'react-bootstrap/esm/Col';
import send from '../../media/images/logos/send_blanc.png';



// const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const otherRegex = /^[a-zA-ZÀ-ÿ\- ]{2,}$/;
// const msgRegex = /^[a-zA-Z0-9À-ÿ\- ]{20,350}$/;

const CreationCompte = () => {


    // d'abord le state
    // les ...
    // puis les render

    const [nom, setNom] = useState('');
    const [validNom, setValidNom] = useState(false);


    const [prenom, setPrenom] = useState('');
    const [validPrenom, setValidPrenom] = useState(false);

    const [entreprise, setEntreprise] = useState('');
    const [validEntreprise, setValidEntreprise] = useState(false);

    const [plateforme, setPlateforme] = useState('');
    const [validPlateforme, setValidPlateforme] = useState(false);

    const [code, setCode] = useState('');
    const [validCode, setValidCode] = useState(false);

    // const [email, setEmail] = useState('');
    // const [validEmail, setValidEmail] = useState(false);
    // const [focusEmail, setFocusEmail] = useState(false);

    // const [object, setObject] = useState('');
    // const [validObject, setValidObject] = useState(false);
    // const [focusObject, setFocusObject] = useState(false);

    // const [msg, setMsg] = useState('');
    // const [validMsg, setValidMsg] = useState(false);
    // const [focusMsg, setFocusMsg] = useState(false);

    // const [errMsg, setErrMsg] = useState([{}]);

    // useEffect(() => {
    //   nameRef.current.focus()
    // }, [])
    // met le cursueur par défaut dans le champ de saisie

    // useEffect(()=>{
    //     emailRef.current.focus()
    //     // met le focus (entoure le rouge) sur l'email lors du chargement de la page avec emailRef et useEffect
    // })

    useEffect(() => {
        const result = otherRegex.test(nom)
        setValidNom(result)
    }, [nom])

    useEffect(() => {
        const result = otherRegex.test(prenom)
        setValidPrenom(result)
    }, [prenom])

    useEffect(() => {
        const result = otherRegex.test(entreprise)
        setValidEntreprise(result)
    }, [entreprise])

    useEffect(() => {
        const result = otherRegex.test(plateforme)
        setValidPlateforme(result)
    }, [plateforme])

    useEffect(() => {
        const result = otherRegex.test(code)
        setValidCode(result)
    }, [code])

    // useEffect(() => {
    //   const result = emailRegex.test(email)
    //   // vérif de la saisie de l'email
    //   setValidEmail(result)
    //   console.log("log email de mon hook : ", email);
    // }, [email])

    // const handleChange = ()=>{
    //     setEmail(emailRef.current.value)
    //     console.log("log email de ma fonction : ", email);
    // }

    // const addMsgError = (msgParam, focusSeter, validElement) => {
    //     focusSeter(false)
    //     const newMsg = validElement ? "" : msgParam;
    //     setErrMsg(newMsg)
    // }

    const handleSubmit = (e) => {
        e.preventDefault();
    }

    return (
        <Wrapper>
            <div className='titre'>
                <h1 className='text'>creation compte</h1>;
            </div>
            <form onSubmit={handleSubmit} className='col-6 mx-auto my-3'>
                <ChampSaisie setValue={setNom} label="Nom :"></ChampSaisie>
                <ChampSaisie setValue={setPrenom} label="Prenom :"></ChampSaisie>
                <ChampSaisie setValue={setEntreprise} label="Entreprise (entreprise représentée en tant que membre d’Initiative Deux-Sèvres :"></ChampSaisie>
                <ChampSaisie setValue={setPlateforme} label="Plateforme Initiative :"></ChampSaisie>
                <ChampSaisie setValue={setCode} label="Code d'accès :"></ChampSaisie>
                <Container fluid>
                    <div className="position-bouton">
                                <button
                                    type="submit"
                                    className="bouton-envoyer"
                                    disabled={!validNom || !validPrenom  || !validEntreprise || !validPlateforme || !validCode}
                                >Envoyer
                                <img src={send} className="img-send" alt="send" />
                                </button>

                            </div>
                </Container>
            </form>
        </Wrapper>
    )
};

export default CreationCompte;
