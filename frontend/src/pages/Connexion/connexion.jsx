import Wrapper from '../../Wrapper'
import { useEffect, useState } from "react";
import { ChampSaisie } from '../CreationCompte/champSaisie';
import '../CreationCompte/creationCompte.css';
import './connexion.css';
import Container from 'react-bootstrap/esm/Container';
import { useNavigate } from 'react-router-dom';


const otherRegex = /^[a-zA-ZÀ-ÿ\- ]{2,}$/; // minimum 2 caractères pour les autres champs
const nomRegex = /^[A-ZÀ-ÿ\- ]{2,}$/; // NOM en MAJUSCULES
const codeRegex = /^[a-zA-ZÀ-ÿ\- ]{1}\d{3}$/; // code admis :  1 lettre suivie de 3 chiffres

const Connexion = () => {

  const [utilisateurDto, setUtilisateurDto] = useState({
        nomUtilisateur: '',
        prenomUtilisateur: '',
        codeUtilisateur: '',
    });

    const [errors, setErrors] = useState({});
    const [userNotFound, setUserNotFound] = useState(false);
    const navigate = useNavigate();

    const validate = () => {
      const newErrors = {};
  
      if (!utilisateurDto.nomUtilisateur) newErrors.nomUtilisateur = 'Le nom est requis';
      if (!utilisateurDto.prenomUtilisateur) newErrors.prenomUtilisateur = 'Le prénom est requis';
      if (!utilisateurDto.codeUtilisateur) newErrors.codeUtilisateur = 'Le code d\'accès est requis';
  
      return newErrors;
  };

    const handleChange = (name, value) => {
        setUtilisateurDto({
            ...utilisateurDto,
            [name]: value
        });

        const newErrors = { ...errors };
        if (value.trim() === '') {
            newErrors[name] = 'Ce champ est requis';
        } else {
            delete newErrors[name];
        }
        setErrors(newErrors);

        // Réinitialiser userNotFound à false lorsque l'utilisateur modifie un champ
        setUserNotFound(false);
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const validationErrors = validate();
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }

        fetch('http://localhost:8080/creationCompte/checkutilisateur', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(utilisateurDto)
        })
        .then(response => response.json())
        .then(data => {
            if (data.exists) {
                navigate('/monCompte'); // Rediriger vers la page "monCompte"
            } else {
                setUserNotFound(true); // Afficher le message "Utilisateur inconnu"
            }
        })
        .catch(error => {
            console.error('Erreur lors de la soumission du formulaire!', error);
        });
    };

    return  (
      <Wrapper>
        <div className='titre'>
                <h1 className='text'>Se connecter</h1>
            </div>
            <form onSubmit={handleSubmit} className='col-6 mx-auto my-3'>
                
              {errors.prenomUtilisateur && <div className="error">{errors.nomUtilisateur}</div>}
              <ChampSaisie
                  setValue={(value) => handleChange('nomUtilisateur', value)}
                  label="Nom :"
                  name="nomUtilisateur"
                  value={utilisateurDto.nomUtilisateur}
                  regex={nomRegex}
              />
              
              {errors.prenomUtilisateur && <div className="error">{errors.prenomUtilisateur}</div>}
              <ChampSaisie  setValue={(value) => handleChange('prenomUtilisateur', value)} label="Prenom :" name="prenomUtilisateur"  value={utilisateurDto.prenomUtilisateur} regex={otherRegex} ></ChampSaisie>
              
              {errors.codeUtilisateur && <div className="error">{errors.codeUtilisateur}</div>}
              <ChampSaisie  setValue={(value) => handleChange('codeUtilisateur', value)} label="Code d'accès :" value={utilisateurDto.codeUtilisateur} name="codeUtilisateur"  regex={codeRegex}  ></ChampSaisie>

              <Container fluid>
                <div className="position-bouton d-flex align-items-center justify-content-end">
                    {userNotFound && (
                      <div>
                          <input
                              className="error-input"
                              value="Utilisateur inconnu"
                              disabled
                          />
                        </div>
                    )}
                    <button
                        type="submit"
                        className="bouton-suivant"
                    >
                        Suivant
                    </button>
                </div>
              </Container>
            </form>
      </Wrapper>
    )
  };
  
  export default Connexion;