<template>
    <div>
        <div class="big-container">
            <OpeningList />
        </div>
        <div>
            <CreateOpening />
        </div>
    </div>
</template>
<script>
import OpeningList from '../components/OpeningList.vue';
import CreateOpening from '../components/CreateOpening.vue';

export default {
    components: {OpeningList, CreateOpening}
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
        padding: 20px;
    }
    
</style>