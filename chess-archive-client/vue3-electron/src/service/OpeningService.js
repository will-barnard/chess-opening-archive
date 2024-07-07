import axios from 'axios';

export default {
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