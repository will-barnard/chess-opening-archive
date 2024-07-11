<template>
    <div>
        <h3>Category Search</h3>
        <input @keyup="updateList()" v-model="search" class="input-search">
        <div v-for="category in categories" :key="category.categoryId" class="item">
            <p @click="$router.push({ name: 'category-detail', params: {categoryId: category.categoryId } })">{{ category.categoryName }}</p>        </div>
    </div>
</template>

<script>


export default {
    data() {
        return {
            search: "",
            categories: []
        }
    },
    methods: {
        updateList() {
            if (this.search) {
                this.categories = this.$store.state.categories.filter(
                    (category) => {
                        return category.categoryName.toLowerCase().includes(this.search.toLowerCase())
                    }
                )
            } else {
                this.categories = [];
            }
        }
    }
}
</script>

<style>
    .input-search {
        width: 80vw;
        margin-bottom: 10px;
    }
</style>