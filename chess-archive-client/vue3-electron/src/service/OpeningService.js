import axios from 'axios';

export default {
    getOpening(openingId) {
        return axios.get('/opening/' + openingId)
    },
    getAllOpenings() {
        return axios.get('/opening')
    },
    createOpening(opening) {
        return axios.post('/opening', opening)
    },
    updateOpening(opening) {
        return axios.put('/opening', opening)
    },
    deleteOpening(openingId) {
        return axios.delete('/opening/' + openingId)
    }
}