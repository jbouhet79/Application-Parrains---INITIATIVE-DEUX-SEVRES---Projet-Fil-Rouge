import Header from './Header'
import Footer from './Footer'
// import '../App.css';
import './index.css';
import './Footer.css';
import './Header.css';


const index = ({ children }) => {
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

export default index;