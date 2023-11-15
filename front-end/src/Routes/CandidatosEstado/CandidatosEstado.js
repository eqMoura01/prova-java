import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

import { useEffect, useState } from "react";
import { contarPacientesPorEstado } from "../../Utils/service";

import './style.css';
import { Link } from 'react-router-dom';
import Header from "../../Components/Header";
import Button from '@mui/material/Button';

function CandidatosEstado() {
  const [dados, setDados] = useState([]);

  useEffect(() => {
    async function loadPacientes() {
      return await contarPacientesPorEstado().then(r => r.data);
    }
    loadPacientes().then(r => setDados(r));
  }, []);

  return (
    <div className="container">
      <Header title='Citel Software'/>
      <div>
        <h1>Candidatos por estado</h1>
        <TableContainer component={Paper} style={{ maxHeight: 500, overflow: 'auto' }}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Estados</TableCell>
                <TableCell align='right' style={{ position: 'sticky', top: 0, backgroundColor: 'white' }}>Quantidade candidatos</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {dados.map((item) => (
                <TableRow key={item.estado}>
                  <TableCell component="th" scope="row">
                    {item.estado}
                  </TableCell>
                  <TableCell component="th" scope="row" align='right'>
                    {item.qtdPacientes}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        <div style={{ marginTop: 20}}>
          <Link to='/'>
            <Button variant="contained" size="large">
              Voltar
            </Button>
          </Link>
        </div>
      </div>
    </div>
  );
}

export default CandidatosEstado;
