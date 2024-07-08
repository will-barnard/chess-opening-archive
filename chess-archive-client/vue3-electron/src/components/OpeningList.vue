<template>
    <div>
        <div>
            <div v-if="$store.state.openings.length > 0">
                <div  v-for="opening in $store.state.openings" :key="opening.openingId">
                    <div class="head-row">
                        <h4>{{opening.openingName}}</h4>
                        <div class="item" v-for="category in opening.categories" :key="category.categoryId">
                            <p>{{ category.categoryName }}</p>
                        </div>
                        <div class="spacer"></div>
                        <div v-show="opening.source" class="detail-row">
                            <p class="item">{{ opening.source.sourceName }}</p>
                            <p class="item">page {{ opening.source.sourcePage }}, var. {{ opening.source.subnumber }}</p>
                        </div>
                    </div>
                    <div @click="opening.showPgn = !opening.showPgn" v-show="!opening.showPgn">
                        <p class="show-pgn-button">PGN</p>
                    </div>
                    <div class="pgn" v-show="opening.showPgn">
                        <div class=pgn-row>
                            <p class="pgn-sub">{{ opening.pgn }}</p>
                            <div class="spacer"></div>
                            <p class="item" @click="copy(opening.pgn)">Copy PGN</p>
                        </div>
                        <p class="notes">{{ opening.notes }}</p>
                        <div @click="opening.showPgn = !opening.showPgn" v-show="opening.showPgn" class="widgets">
                            <p class="show-pgn-button">Hide</p>
                            <img src="/img/edit.png" class="edit" @click="editOpening(opening)"/>
                            <img src="/img/trash.png" class="trash" @click="deleteOpening(opening)"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    methods: {
        copy(pgn) {
            navigator.clipboard.writeText(pgn);
        },
        deleteOpening(opening) {
            this.$store.commit("DELETE_OPENING", opening.openingId);
        },
        editOpening(opening) {
            // todo do this!!
            opening;
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
        height: 2em;
        margin: 5px;
        border: 1px solid;
        padding: 5px;
        border-radius: 10px;
    }
    .trash {        
        background-color: #c92020;
    }
    .edit {
        background-color: #c9aa20;
    }
</style>