// import './App.css';
import { Routes, Route } from 'react-router-dom';
import Accueil from './pages/Accueil/accueil';
import MonCompte from './pages/MonCompte/monCompte';
import Profils from './pages/Profils/profils';
import Matchs from './pages/Matchs/matchs';
import Messages from './pages/Messages/messages';
import Ressources from './pages/Ressources/ressources';
import Indicateurs from './pages/Indicateurs/indicateurs';
import Connexion from './pages/Connexion/connexion';
import CreationCompte from './pages/CreationCompte/creationCompte';

function App() {
  return (
    <Routes>
      <Route path='/' element={<Accueil/>}></Route>
      <Route path='/monCompte' element={<MonCompte/>}></Route>
      <Route path='/profils' element={<Profils/>}></Route>
      <Route path='/matchs' element={<Matchs/>}></Route>
      <Route path='/messages' element={<Messages/>}></Route>
      <Route path='/ressources' element={<Ressources/>}></Route>
      <Route path='/indicateurs' element={<Indicateurs/>}></Route>
      <Route path='/connexion' element={<Connexion/>}></Route>
      <Route path='/creationCompte' element={<CreationCompte/>}></Route>
    </Routes>
  );
}

export default App;

