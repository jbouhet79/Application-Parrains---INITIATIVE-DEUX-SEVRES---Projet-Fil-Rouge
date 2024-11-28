import Header from './Header'
import Footer from './Footer'
// import '../App.css';
import './index.css';
import './Footer.css';
import './Header.css';


const Index = ({ children }) => {
    return (
        <>
            <Header />
                <main>
                    {children}
                </main>
            <Footer />
            
        </>
    );
};

export default Index;