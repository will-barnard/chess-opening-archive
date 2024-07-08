<template>
    <div>
        <div>
            <div v-if="$store.state.openings.length > 0" class="opening-container">
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
                        <div class="custom-spacer"></div>
                        <p class="pgn-sub">{{ opening.notes }}</p>
                        <div @click="opening.showPgn = !opening.showPgn" v-show="opening.showPgn">
                        <p class="show-pgn-button">Hide</p>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {

        }
    },
    methods: {
        copy(pgn) {
            navigator.clipboard.writeText(pgn);
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
    .custom-spacer {
        height: 15px;
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
</style>