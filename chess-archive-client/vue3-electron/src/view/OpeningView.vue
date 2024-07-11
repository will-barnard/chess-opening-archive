<template>
    <div>
        <div class="menu">
            <h3 @click="showCreate=true" class="hp clicker">Create Opening</h3>
            <h3 @click="showCreate=false" class="hp clicker">Opening List</h3>
        </div>
        <div v-if="!showCreate">
            <OpeningList />
        </div>
        <div v-if="showCreate">
            <CreateOpening @clicked="reset"/>
        </div>
    </div>
</template>
<script>
import OpeningList from '../components/OpeningList.vue';
import CreateOpening from '../components/CreateOpening.vue';

export default {
    data() {
        return {
            showCreate: false
        }
    },
    components: {OpeningList, CreateOpening},
    methods: {
        reset(boolean) {
            if (boolean) {
                this.showCreate = false;
            }
        }
    }
}
</script>
<style>
    .container {
        display: flex;
        flex-direction: column;
        justify-content: start;
        align-items: center;
        border: 1px solid;
        border-radius: 10px;
        padding: 20px;
        margin: 15px;
    }
    .subcontainer {
        width: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: start;
        margin: 10px;
    }
    .big-container {
        border: 1px solid;
        border-radius: 10px;
        margin: 15px;
        padding: 10px;
    }
    .menu {
        display: flex;
        flex-direction: row-reverse;
    }
    .clicker {
        user-select: none;
    }
    .clicker:hover {
        cursor: pointer;
        opacity: 70%;
        transition: opacity .2s;
    }
</style>