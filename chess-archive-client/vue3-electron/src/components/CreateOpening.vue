<template>
    <div>
        <h1>New Opening</h1>
        <form>
            <div class="container">
                <div class="subcontainer">
                    <label>Opening Name</label>
                    <input v-model="newOpening.openingName">
                </div>
                
                <div class="subcontainer">
                    <label>PGN</label>
                    <textarea v-model="newOpening.pgn"></textarea>
                </div>
                <div class="subcontainer">
                    <label>Notes</label>
                    <textarea v-model="newOpening.notes"></textarea>
                </div>
                
            </div>
            <div class="container">
                <div v-for="category in newCategories" :key="category.categoryId" class="new-category">
                    <img src="/img/minus.png" @click="removeCategory(category)">
                    {{ category.categoryName }}
                </div>
                <div class="subcontainer">
                    <label>Categories</label>
                    <div class="button-row">
                        <input @keyup="updateCategory()" v-model="categorySearch">
                        <button type="button" @click="newCategory()">Create&nbsp;New&nbsp;Category</button>
                    </div>
                </div>
                <div class="categories">
                        <div v-for="category in categories" :key="category.categoryId" class="category">
                            <img src="/img/plus.png" @click="addCategory(category)">
                            <p>{{category.categoryName}}</p>
                        </div>
                    </div>
                <div>

                </div>
            </div>
            <div class="container">
                <div class="subcontainer">
                    <div class="new-source" v-show="newSource.sourceId"> 
                        <img src="/img/minus.png" @click="removeCategory(category)">
                        <h3>{{newSource.sourceName}}</h3>
                    </div>
                    <div class="container-row">
                        <label class="container-row">Source</label>
                        <input class="container-row" @keyup="updateSource()" v-model="sourceSearch">
                        <label class="container-row">Page</label>
                        <input v-model="newSource.sourcePage" class="container-row">
                        <label class="container-row">Variation</label>
                        <input v-model="newSource.subnumber" class="container-row">
                    </div>
                    <div class="sources">
                        <div v-for="source in sources" :key="source.sourceId" class="source">
                            <img src="/img/plus.png" @click="addSource(source)">
                            <p>{{source.sourceName}}</p>
                        </div>
                    </div>
                </div>
            </div>
            <button type="button" @click="submit">Submit</button>
        </form>
    </div>
</template>
<script>
import CategoryService from '@/service/CategoryService';

export default {
    data() {
        return {
            newOpening: {},
            newCategories: [],
            categories: [],
            categorySearch: "",
            newSource: {},
            sources: [],
            sourceSearch: ""
        }
    },
    methods: {
        submit() {
            this.newOpening.categories = this.categories;
            this.newOpening.source = this.newSource;
            this.$store.commit("CREATE_OPENING", this.newOpening);
            this.$router.push('/');
        },
        updateSource() {
            if (!this.sourceSearch) {
                this.sources = [];
            } else {
                this.sources = this.$store.state.sources.filter(
                    (source) => {
                        return source.sourceName.toLowerCase().includes(this.sourceSearch.toLowerCase())
                    }
                )
            }
        },
        addSource(source) {
            this.newSource = source;
            this.sourceSearch = "";
        },
        removeSource() {
            this.newSource = {};
        },
        updateCategory() {
            if (!this.categorySearch) {
                this.categories = [];
            } else {
                this.categories = this.$store.state.categories.filter(
                    (category) => {
                        return category.categoryName.toLowerCase().includes(this.categorySearch.toLowerCase())
                    }
                )
            }
        },
        addCategory(category) {
            this.newCategories.push(category);
            this.categorySearch = "";
        },
        removeCategory(category) {
            this.newCategories = this.newCategories.filter(
                (cat) => {
                    return cat.categoryId != category.categoryId;
                }
            )
        },
        newCategory() {
            let cat = {};
            cat.categoryName = this.categorySearch;
            CategoryService.createCategory(cat).then(
                (response) => {
                    this.newCategories.push(response.data);
                    this.$store.commit('CREATE_CATEGORY', response.data);
                }
            )
        }
    }
}
</script>
<style>
    input {
        width: 100%;
    }
    textarea {
        width: 100%;
        margin-bottom: 10px;
    }
    .container-row {
        display: flex;
    }
    input.container-row, label.container-row {
        margin: 10px;
    }
    .source {
        display: flex;
        flex-direction: row;
        align-items: center;
        padding: 5px;
        border: 1px solid;
        border-radius: 10px;
        margin: 0px;
    }
    .source p {
        margin: 0px;
    }
    .source img {
        height: .9em;
        margin-right: 10px;
        margin-left: 10px;
        border: 1px solid;
        border-radius: 5px;
        background-color: #20c92b;
        padding: 5px;
    }
    .button-row {
        display: flex;
        width: 100%;
    }
    .category {
        display: flex;
        flex-direction: row;
        align-items: center;
        padding: 5px;
        border: 1px solid;
        border-radius: 10px;
        margin: 0px;
    }
    .category p {
        margin: 0px;
    }
    .category img {
        height: .9em;
        margin-right: 10px;
        margin-left: 10px;
        border: 1px solid;
        border-radius: 5px;
        background-color: #20c92b;
        padding: 5px;
    }
    .categories {
        display: flex;
        justify-content: start;
    }
    .new-category {
        display: flex;
        flex-direction: row;
        align-items: center;
        padding: 5px;
        border: 1px solid;
        border-radius: 10px;
        margin: 0px;
    }
    .new-category p {
        margin: 0px;

    }
    .new-category img {
        height: .9em;
        margin-right: 10px;
        margin-left: 10px;
        border: 1px solid;
        border-radius: 5px;
        background-color: #c92020;
        padding: 5px;
    }
    .new-source {
        display: flex;
        flex-direction: row;
        align-items: center;
        padding: 5px;
        border: 1px solid;
        border-radius: 10px;
        margin: 0px;
    }
    .new-source img {
        height: .9em;
        margin-right: 10px;
        margin-left: 10px;
        border: 1px solid;
        border-radius: 5px;
        background-color: #c92020;
        padding: 5px;
    }
</style>