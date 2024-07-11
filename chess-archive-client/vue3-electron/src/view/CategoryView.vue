<template>
    <div>
        <div class="menu">
            <h3 @click="$router.push({ name: 'home' })" class="hp clicker">Opening List</h3>
        </div>
        <div>
            <OpeningCategoryList :filterCategoryId="$route.params.categoryId" />
        </div>
    </div>
</template>


<script>
import OpeningCategoryList from '../components/OpeningCategoryList.vue';

export default {
    components: {OpeningCategoryList},
    created() {
        if (!this.$route.params.categoryId) {
            this.categoryId = this.$route.params.categoryId
        }
    },
    data() {
        return {
            categoryId: 0
        }
    }
}
</script>


<style>
    
</style>