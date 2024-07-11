<template>
    <div>
        <div>
            <div v-if="$store.state.openings.length > 0">
                <div  v-for="opening in $store.state.openings" :key="opening.openingId">

                    <OpeningCard :opening="opening"/>
                      
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import OpeningCard from '@/components/OpeningCard.vue';

export default {
    components: {OpeningCard},
    data() {
        return {
            categories: [],
            categorySearch: "",
            sources: [],
            sourceSearch: ""
        }
    }
}
</script>
<style>
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
    .widgets {
        display: flex;
        justify-content: flex-end;
    }
    .trash:hover {        
        background-color: #c92020;
        transition: background-color .4s;
    }
    .edit:hover {
        background-color: #c9aa20;
        transition: background-color .4s;
    }
    .not-editing {
        border: 1px solid;
        border-radius: 10px;
        padding: 5px;
        margin: 10px;
        background-color: rgb(83, 83, 83);
    }
    .editing input, textarea {
        width: 80%;
    }
</style>