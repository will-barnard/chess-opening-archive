<template>
    <div>
        <div>
            <div v-if="$store.state.openings.length > 0">
                <div  v-for="opening in $store.state.openings.filter(
                    (opening) => {
                        return opening.categories.find(
                            (category) => {
                                return category.categoryId == filterCategoryId;
                            }
                        )
                    }
                )" :key="opening.openingId">
                    <div class="not-editing">
                        <div class="head-row">
                            <h4>{{opening.openingName}}</h4>
                            <div class="item" v-for="category in opening.categories" :key="category.categoryId">
                                <p @click="$router.push({ name: 'category-detail', params: {categoryId: category.categoryId } })">{{ category.categoryName }}</p>
                            </div>
                            <div class="spacer"></div>
                            <div v-show="opening.source" class="detail-row">
                                <p class="item">{{ opening.source.sourceName }}</p>
                                <p class="item">page {{ opening.source.sourcePage }}, var. {{ opening.source.subnumber }}</p>
                            </div>
                        </div>
                        <div @click="opening.showPgn = !opening.showPgn" v-show="!opening.showPgn">
                            <p class="show-pgn-button">Show</p>
                        </div>
                        <div class="pgn" v-show="opening.showPgn">
                            <div class=pgn-row>
                                <p class="pgn-sub">{{ opening.pgn }}</p>
                                <div class="spacer"></div>
                                <p class="item" @click="copy(opening.pgn)">Copy PGN</p>
                            </div>
                            <p class="notes">{{ opening.notes }}</p>
                            <div class="widgets">
                                <img src="/img/edit.png" class="edit" @click="editOpening(opening)"/>
                                <img src="/img/trash.png" class="trash" @click="deleteOpening(opening)"/>
                            </div>
                            <div @click="opening.showPgn = !opening.showPgn" v-show="opening.showPgn">
                                <p class="show-pgn-button">Hide</p>
                            </div>
                        </div>
                    </div>
                    <div class="editing" v-show="opening.editing">
                        <label>Opening Name</label>
                        <input v-model="opening.editOpening.openingName">
                        <label>Source</label>
                        <div class="new-source" v-show="opening.editOpening.source.sourceId"> 
                            <img src="/img/minus.png" @click="removeSource(opening.editOpening)">
                            <h3>{{opening.editOpening.source.sourceName}}</h3>
                        </div>
                        <input class="container-row" @keyup="updateSource(opening.editOpening)" v-model="sourceSearch">
                        <div class="sources">
                            <div v-for="source in sources" :key="source.sourceId" class="source">
                                <img src="/img/plus.png" @click="addSource(source, opening.editOpening)">
                                <p>{{source.sourceName}}</p>
                            </div>
                        </div>
                        <input v-model="opening.editOpening.source.sourcePage">
                        <input v-model="opening.editOpening.source.subnumber">
                        <textarea v-model="opening.editOpening.pgn"></textarea>
                        <textarea v-model="opening.editOpening.notes"></textarea>
                        <div v-for="category in opening.editOpening.categories" :key="category.categoryId" class="new-category">
                            <img src="/img/minus.png" @click="removeCategory(category, opening.editOpening)">
                            {{ category.categoryName }}
                        </div>
                        <div class="subcontainer">
                            <label>Categories</label>
                            <div class="button-row">
                                <input @keyup="updateCategory()" v-model="categorySearch">
                                <button type="button" @click="newCategory(opening.editOpening)">Create&nbsp;New&nbsp;Category</button>
                            </div>
                        </div>
                        <div class="categories">
                            <div v-for="category in categories" :key="category.categoryId" class="category">
                                <img src="/img/plus.png" @click="addCategory(category, opening.editOpening)">
                                <p>{{category.categoryName}}</p>
                            </div>
                        </div>
                        <button @click="updateOpening(opening.editOpening); opening.showPgn = false;">Submit</button>
                        <button @click="cancelUpdateOpening(opening); opening.showPgn = false;">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import CategoryService from '@/service/CategoryService';

export default {
    props: ['filterCategoryId'],
    data() {
        return {
            categories: [],
            categorySearch: "",
            sources: [],
            sourceSearch: ""
        }
    },
    methods: {
        copy(pgn) {
            navigator.clipboard.writeText(pgn);
        },
        deleteOpening(opening) {
            this.$store.commit("DELETE_OPENING", opening.openingId);
        },
        editOpening(opening) {
            opening.editing = !opening.editing;
        },
        updateOpening(opening) {
            this.$store.commit("UPDATE_OPENING", opening);
        },
        cancelUpdateOpening(opening) {
            this.$store.commit("CANCEL_UPDATE_OPENING", opening);
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
    h4, p {
        margin: 0px;
    }
    .head-row {
        display: flex;
        align-items: center;
        margin: 0px;
    }
    .detail-row {
        display: flex;
        flex-direction: row;
        align-items: center;
        margin: 0px;
    }
    .item {
        padding: 5px;
        border: 1px solid;
        border-radius: 10px;
        margin: 5px;
        user-select: none;
    }
    .item:hover {
        cursor: pointer;
        opacity: 70%;
        transition: opacity .2s;
    }
    .opening-container {
        display: flex;
        flex-direction: column;
        justify-content: start;
        width: 1;
        border: 1px solid;
        border-radius: 10px;
        padding: 10px;
        margin: 15px;
        text-align: left;
    }
    .pgn {
        padding: 5px;
        border: 1px solid;
        border-radius: 10px;
    }
    .show-pgn-button {
        padding: 5px;
        border: 1px solid;
        border-radius: 10px;
        text-align: center;
        user-select: none;
        background-color: #3a3a3a;
    }
    .show-pgn-button:hover {
        cursor: pointer;
        opacity: 70%;
        transition: opacity .2s;
    }
    .pgn-sub {
        padding: 10px;
    }
    .pgn-row {
        display: flex;
    }
    .notes {
        text-align: left;
        margin: 10px;
    }
    .widgets img {
        cursor: pointer;
        height: 2em;
        margin: 5px;
        border: 1px solid;
        padding: 5px;
        border-radius: 10px;
    }
    .widgets img:hover {
        opacity: 70%;
        transition: opacity .2s;
    }
    .trash {        
        background-color: #c92020;
    }
    .edit {
        background-color: #c9aa20;
    }
    .not-editing {
        border: 1px solid;
        border-radius: 10px;
        padding: 5px;
        margin: 10px;
        background-color: rgb(83, 83, 83);
    }
</style>