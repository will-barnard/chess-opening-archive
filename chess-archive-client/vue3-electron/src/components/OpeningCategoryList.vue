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
                    
                    <OpeningCard :opening="opening" />

                </div>
            </div>
        </div>
    </div>
</template>
<script>
import CategoryService from '@/service/CategoryService';
import OpeningCard from './OpeningCard.vue';

export default {
    components: {OpeningCard},
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