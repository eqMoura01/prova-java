import axios from "axios";

const BASE_URL = "http://localhost:8080/paciente";

export const salvarPaciente = paciente => {
    return axios.post(`${BASE_URL}`, paciente);
}

export const salvarListaPacientes = listaPacientes => {
    return axios.post(`${BASE_URL}/list`, listaPacientes);
}

export const listarPacientes = () => {
    return axios.get(`${BASE_URL}`);
}

export const buscarPacientePorId = id => {
    return axios.get(`${BASE_URL}/${id}`);
}

export const contarPacientesPorEstado = () => {
    return axios.get(`${BASE_URL}/estados`);
}

export const calcularIMC = () => {
    return axios.get(`${BASE_URL}/imc`);
}

export const calcularMediaIdadePorTipoSanguineo = () => {
    console.log("CHAMOU API", `${BASE_URL}/mediaIdadeSanguinea`)
    return axios.get(`${BASE_URL}/mediaIdadeSanguinea`);
}

export const contarDoadoresPorTipoSanguineo = () => {
    return axios.get(`${BASE_URL}/qtdDoadorTipoSangue`);
}

export const calcularPercentualObesidadePorSexo = () => {
    return axios.get(`${BASE_URL}/percentualObesoSexo`);
}

export const atualizarPaciente = paciente => {
    return axios.put(`${BASE_URL}`, paciente);
}

export const excluirPacientePorId = id => {
    return axios.delete(`${BASE_URL}/${id}`);
}
