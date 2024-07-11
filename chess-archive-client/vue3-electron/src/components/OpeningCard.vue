<template>
    <div>
        <div class="not-editing">
            <div class="head-row">
                <h4>{{opening.openingName}}</h4>
                <div class="item" v-for="category in opening.categories" :key="category.categoryId">
                    <p @click="$router.push({ name: 'category-detail', params: {categoryId: category.categoryId } })">{{ category.categoryName }}</p>
                </div>
                <div class="spacer"></div>
                <div v-show="opening.source" class="detail-row">
                    <p class="item">{{ opening.source.sourceName }}</p>
                    <p class="item" @click="$router.push({ name: 'source', params: {sourceId: opening.source.sourceId, sourcePage: opening.source.sourcePage } } )">page {{ opening.source.sourcePage }}, var. {{ opening.source.subnumber }}</p>
                </div>
            </div>
            <div @click="showPgn = !showPgn" v-show="!showPgn">
                <p class="show-pgn-button">Show</p>
            </div>
            <div class="pgn" v-show="showPgn">
                <div class=pgn-row>
                    <p class="pgn-sub">{{ opening.pgn }}</p>
                    <div class="spacer"></div>
                    <p class="item" @click="copy(opening.pgn)">Copy PGN</p>
                </div>
                <p class="notes">{{ opening.notes }}</p>
                <div class="widgets">
                    <img src="/img/edit.png" class="edit" @click="isEditing = !isEditing"/>
                    <img src="/img/trash.png" class="trash" @click="deleteOpening(opening)"/>
                </div>
                <div @click="showPgn = !showPgn" v-show="showPgn">
                    <p class="show-pgn-button">Hide</p>
                </div>
            </div>
            <div class="editing" v-if="isEditing">
                <label>Opening Name</label>
                <input v-model="editOpening.openingName">
                <label>Source</label>
                <input class="container-row" @keyup="updateSource(editOpening)" v-model="sourceSearch">
                <div class="new-source" v-show="editOpening.source.sourceId"> 
                    <img src="/img/minus.png" @click="removeSource(opening.editOpening)">
                    <h4>{{editOpening.source.sourceName}}</h4>
                </div>
                <div class="sources">
                    <div v-for="source in sources" :key="source.sourceId" class="source">
                        <img src="/img/plus.png" @click="addSource(source, editOpening)">
                        <p>{{source.sourceName}}</p>
                    </div>
                </div>
                <div>
                    <label>Page</label>
                    <input v-model="editOpening.source.sourcePage">
                </div>
                <div>
                    <label>Variation</label>
                    <input v-model="editOpening.source.subnumber">
                </div>
                <div>
                    <label>PGN</label>
                    <textarea v-model="editOpening.pgn"></textarea>
                </div>
                <div>
                    <label>Notes</label>
                    <textarea v-model="editOpening.notes"></textarea>
                </div>
                <div v-for="category in editOpening.categories" :key="category.categoryId" class="new-category">
                    <img src="/img/minus.png" @click="removeCategory(category, editOpening)">
                    {{ category.categoryName }}
                </div>
                <div class="subcontainer">
                    <label>Categories</label>
                    <div class="button-row">
                        <input @keyup="updateCategory()" v-model="categorySearch">
                        <button type="button" @click="newCategory(editOpening)">Create&nbsp;New&nbsp;Category</button>
                    </div>
                </div>
                <div class="categories">
                    <div v-for="category in categories" :key="category.categoryId" class="category">
                        <img src="/img/plus.png" @click="addCategory(category, editOpening)">
                        <p>{{category.categoryName}}</p>
                    </div>
                </div>
                <button @click="updateOpening(editOpening); showPgn = false;">Submit</button>
                <button @click="cancelUpdateOpening(); showPgn = false;">Cancel</button>
            </div>
        </div>

        
        
    </div>
</template>
<script>
import CategoryService from '@/service/CategoryService.js';

export default {
    props: ['opening'],
    data() {
        return {
            categories: [],
            categorySearch: "",
            sources: [],
            sourceSearch: "",
            showPgn: false,
            editOpening: {},
            isEditing: false
        }
    },
    created() {
        this.editOpening = JSON.parse(JSON.stringify(this.opening));
    },
    methods: {
        copy(pgn) {
            navigator.clipboard.writeText(pgn);
        },
        deleteOpening(opening) {
            this.$store.commit("DELETE_OPENING", opening.openingId);
        },
        updateOpening(opening) {
            this.$store.commit("UPDATE_OPENING", opening);
            this.isEditing = false;
        },
        cancelUpdateOpening() {
            this.isEditing = false;
            this.newOpening = JSON.parse(JSON.stringify(this.opening))
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
        addCategory(category, opening) {
            opening.categories.push(category);
            this.categorySearch = "";
        },
        removeCategory(category, opening) {
            opening.categories = opening.categories.filter(
                (cat) => {
                    return cat.categoryId != category.categoryId;
                }
            )
        },
        newCategory(opening) {
            let cat = {};
            cat.categoryName = this.categorySearch;
            CategoryService.createCategory(cat).then(
                (response) => {
                    opening.categories.push(response.data);
                    this.$store.commit('CREATE_CATEGORY', response.data);
                }
            )
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
        addSource(source, opening) {
            opening.source.sourceName = source.sourceName;
            opening.source.sourceId = source.sourceId;
            this.sourceSearch = "";
        },
        removeSource(opening) {
            opening.source.sourceId = 0;
            opening.source.sourceName = 0;
        }
    }
}
</script>
<style scoped>
    .editing {
        border: 1px solid;
        border-radius: 10px;
        padding: 5px;
    }
</style>