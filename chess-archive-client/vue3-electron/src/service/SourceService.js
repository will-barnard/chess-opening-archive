import axios from 'axios';

export default {
    getSource(sourceId) {
        return axios.get('/source/' + sourceId)
    },
    createSource(source) {
        return axios.post('/source', source)
    },
    updateSource(source) {
        return axios.put('/source', source)
    },
    deleteSource(sourceId) {
        return axios.delete('/source/' + sourceId)
    }
}