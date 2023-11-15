import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./Home";
import CandidatosEstado from "./Routes/CandidatosEstado/CandidatosEstado"; // Importe os novos componentes
import ImcFaixaIdade from "./Routes/ImcFaixaIdade/ImcFaixaIdade";
import PercentualObesosSexo from "./Routes/PercentualObesosSexo/PercentualObesosSexo"
import MediaIdadeTipoSanguineo from "./Routes/MediaIdadeTipoSanguineo";
import QuantidadePossiveisDoadores from "./Routes/QuantidadePossiveisDoadores/QuantidadePossiveisDoadores";
import './App.css';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/candidatos-estado" element={<CandidatosEstado/>}/>
                <Route path="/imc-faixa-idade" element={<ImcFaixaIdade/>}/>
                <Route path="/percentual-obesos" element={<PercentualObesosSexo/>}/>
                <Route path="/media-idade-tipo-sanguineo" element={<MediaIdadeTipoSanguineo/>}/>
                <Route path="/quantidade-possiveis-doadores" element={<QuantidadePossiveisDoadores/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
