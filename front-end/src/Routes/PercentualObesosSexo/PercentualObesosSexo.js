import React, { useEffect, useState } from "react";
import { calcularPercentualObesidadePorSexo } from "../../Utils/service";
import { Link } from "react-router-dom";
import Header from "../../Components/Header";
import Button from "@mui/material/Button";
import './style.css';

function PercentualObesidade() {
    const [dados, setDados] = useState({
        percentualObesosMasc: 0,
        percentualObesosFem: 0,
    });

    useEffect(() => {
        async function loadPercentualObesidade() {
            try {
                const response = await calcularPercentualObesidadePorSexo();
                setDados(response.data);
            } catch (error) {
                alert("Erro ao carregar os dados de percentual de obesidade:", error);
            }
        }

        loadPercentualObesidade();
    }, []);

    return (
        <div className='container'>
            <Header title='Citel Software' />
            <h1>Percentual de Obesidade</h1>
            <div style={{ width: '60%' }}>
                <table>
                    <thead>
                        <tr>
                            <th>Gênero</th>
                            <th style={{ textAlign: "right" }}>Percentual de Obesidade</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Homens</td>
                            <td style={{ textAlign: "right" }}>{dados.percentualObesosMasc.toFixed(2)}%</td>
                        </tr>
                        <tr>
                            <td>Mulheres</td>
                            <td style={{ textAlign: "right" }}>{dados.percentualObesosFem.toFixed(2)}%</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div style={{ marginTop: 20 }}>
                <Link to='/'>
                    <Button variant="contained" size="large">
                        Voltar
                    </Button>
                </Link>
            </div>
        </div>
    );
}

export default PercentualObesidade;
