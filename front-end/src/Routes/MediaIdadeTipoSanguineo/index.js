import { useEffect, useState } from "react";

import { FormControl, InputLabel, MenuItem, Select } from "@mui/material";
import { calcularMediaIdadePorTipoSanguineo } from "../../Utils/service";

import { Link } from "react-router-dom";
import './style.css';
import Header from "../../Components/Header";
import Button from "@mui/material/Button";
import * as React from "react";

function MediaIdadeTipoSanguineo() {
  const [mediaIdade, setMediaIdade] = useState(0);
  const [mediaIdadeTipoSanguineo, setMediaIdadeTipoSanguineo] = useState([]);

  useEffect(() => {
    async function loadMediaIdadeTipoSanguineo() {
      return await calcularMediaIdadePorTipoSanguineo().then(r => r.data)
    }
    loadMediaIdadeTipoSanguineo().then(r => { setMediaIdadeTipoSanguineo(r) });
  }, []);

  return (
    <div className="container">
      <Header title='Citel Software'/>
      <div>
        <h1>Média de idade por tipo sanguíneo</h1>
        <FormControl fullWidth>
          <InputLabel id="demo-simple-select-label">Tipo</InputLabel>
          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={mediaIdade}
            label="Age"
            onChange={(event) => {
              setMediaIdade(event.target.value.mediaIdade)
            }}
          >
            {mediaIdadeTipoSanguineo.map((item) => (
              <MenuItem
                key={item.tipoSanguineo}
                value={item}>{item.tipoSanguineo}</MenuItem>))}
          </Select>
        </FormControl>
        <div className='mediaIdadeContainer'>
          {mediaIdade} anos
        </div>
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
export default MediaIdadeTipoSanguineo;
