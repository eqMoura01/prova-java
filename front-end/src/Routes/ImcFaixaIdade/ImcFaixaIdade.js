import React, { useEffect, useState } from "react";
import { calcularIMC } from "../../Utils/service";
import { FormControl, InputLabel, MenuItem, Select } from "@mui/material";
import { Link } from "react-router-dom";
import './style.css'
import Header from "../../Components/Header";
import Button from "@mui/material/Button";

function ImcFaixaIdade() {
  const [imcFaixaIdade, setImcFaixaIdade] = useState([]);
  const [selectedFaixa, setSelectedFaixa] = useState(null);

  useEffect(() => {
    async function loadImcFaixaIdade() {
      return await calcularIMC().then((r) => r.data);
    }

    loadImcFaixaIdade().then((r) => {
      setImcFaixaIdade(r);
    });
  }, []);

  const handleFaixaChange = (event) => {
    const selectedFaixa = event.target.value;
    setSelectedFaixa(selectedFaixa);
  };

  return (
    <div className="container">
      <Header title='Citel Software'/>
      <div>
        <p><Link to="/">Voltar</Link></p>
        <h1>IMC Faixa Idade</h1>
        <FormControl fullWidth>
          <InputLabel id="demo-simple-select-label">Faixa etária</InputLabel>
          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={selectedFaixa}
            label="Faixa etária"
            onChange={handleFaixaChange}
          >
            {imcFaixaIdade.map((item) => (
              <MenuItem key={item.faixaIdadeInicio} value={item}>
                {`${item.faixaIdadeInicio} - ${item.faixaIdadeFim}`}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
        {selectedFaixa && (
          <h1>IMC Médio: {selectedFaixa.imcMedio.toFixed(2)}</h1>
        )}
      </div>
      <div style={{ marginTop: 20}}>
        <Link to='/'>
          <Button variant="contained" size="large">
            Voltar
          </Button>
        </Link>
      </div>
    </div>
  );
}

export default ImcFaixaIdade;
