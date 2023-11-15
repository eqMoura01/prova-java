import './App.css';
import {Link} from "react-router-dom";
import Header from "./Components/Header";

function Home() {
    return (
        <div className="App">
            <Header title='Citel Software'/>
            <div>
                <ul>
                    <Link className='link' to={"/candidatos-estado"}>
                        <li>Quantidade de candidatos por estado</li>
                    </Link>
                    <Link className='link' to={"/imc-faixa-idade"}>
                        <li>IMC por faixa de idade</li>
                    </Link>
                    <Link className='link' to={"/percentual-obesos"}>
                        <li>Percentual de obesos por sexo</li>
                    </Link>
                    <Link className='link' to={"/media-idade-tipo-sanguineo"}>
                        <li>Média de idade por tipo sanguíneo</li>
                    </Link>
                    <Link className='link' to={"/quantidade-possiveis-doadores"}>
                        <li>Quantidade de possíveis doadores</li>
                    </Link>
                </ul>
            </div>
        </div>
    );
}

export default Home;
