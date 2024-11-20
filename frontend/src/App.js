// import './App.css';
import { Routes, Route } from 'react-router-dom';
import Accueil from './pages/Accueil/Accueil';
import MonCompteParrain from './pages/MonCompteParrain/MonCompteParrain';
import Profils from './pages/Profils/Profils';
import Matchs from './pages/Matchs/Matchs';
import Messages from './pages/Messages/Messages';
import Ressources from './pages/Ressources/Ressources';
import Indicateurs from './pages/Indicateurs/Indicateurs';
import Connexion from './pages/Connexion/Connexion';
import CreationCompte from './pages/CreationCompte/CreationCompte';
import Filtres from './pages/Filtres/Filtres';
import { AuthProvider } from './AuthContext';

function App() {
  return (
    <AuthProvider>
      <Routes>
        <Route path='/' element={<Accueil/>}></Route>
        <Route path='/monCompteParrain' element={<MonCompteParrain/>}></Route>
        <Route path='/profils' element={<Profils/>}></Route>
        <Route path='/matchs' element={<Matchs/>}></Route>
        <Route path='/messages' element={<Messages/>}></Route>
        <Route path='/ressources' element={<Ressources/>}></Route>
        <Route path='/indicateurs' element={<Indicateurs/>}></Route>
        <Route path='/connexion' element={<Connexion/>}></Route>
        <Route path='/creationCompte' element={<CreationCompte/>}></Route>
        <Route path='/filtres' element={<Filtres/>}></Route>
      </Routes>
    </AuthProvider>
  );
}

export default App;

