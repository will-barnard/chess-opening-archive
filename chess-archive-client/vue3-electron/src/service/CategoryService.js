import axios from 'axios';

export default {
    getCategory(categoryId) {
        return axios.get('/category/' + categoryId)
    },
    getAllCategories() {
        return axios.get('/category')
    },
    createCategory(category) {
        return axios.post('/category', category)
    },
    updateCategory(category) {
        return axios.put('/category', category)
    },
    deleteCategory(categoryId) {
        return axios.delete('/category/' + categoryId)
    },
    addCategoryToOpening(categoryId, openingId) {
        return axios.post('/category/' + categoryId + '/' + openingId)
    },
    removeCategoryFromOpening(categoryId, openingId) {
        return axios.delete('/category/' + categoryId + '/' + openingId)
    }
}