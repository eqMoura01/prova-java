import React, { useEffect, useState } from "react";
import { contarDoadoresPorTipoSanguineo } from "../../Utils/service";
import { FormControl, InputLabel, MenuItem, Select } from "@mui/material";
import { Link } from "react-router-dom";
import './style.css';
import Header from "../../Components/Header";
import Button from "@mui/material/Button";

function QuantidadeDoadoresPorTipoReceptor() {
  const [doadoresPorTipoReceptor, setDoadoresPorTipoReceptor] = useState([]);
  const [selectedTipoReceptor, setSelectedTipoReceptor] = useState(null);

  useEffect(() => {
    async function loadDoadoresPorTipoReceptor() {
      return await contarDoadoresPorTipoSanguineo().then((r) => r.data);
    }

    loadDoadoresPorTipoReceptor().then((r) => {
      setDoadoresPorTipoReceptor(r);
    });
  }, []);

  const handleTipoReceptorChange = (event) => {
    const selectedTipoReceptor = event.target.value;
    setSelectedTipoReceptor(selectedTipoReceptor);
  };

  return (
    <div className="container">
      <Header title='Citel Software'/>
      <div>
        <h1>Quantidade de Doadores por Tipo de Receptor</h1>
        <FormControl fullWidth>
          <InputLabel id="demo-simple-select-label">Tipo de Receptor</InputLabel>
          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={selectedTipoReceptor}
            label="Tipo de Receptor"
            onChange={handleTipoReceptorChange}
          >
            {doadoresPorTipoReceptor.map((item) => (
              <MenuItem key={item.tipoReceptor} value={item}>
                {item.tipoReceptor}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
        {selectedTipoReceptor && (
          <h1>Quantidade de Doadores: {selectedTipoReceptor.quantidadeDoadores}</h1>
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

export default QuantidadeDoadoresPorTipoReceptor;
