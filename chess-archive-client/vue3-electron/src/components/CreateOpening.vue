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
                <div class="subcontainer">
                    <label>Categories</label>
                    <input>
                </div>
                <div>

                </div>
            </div>
            <div class="container">
                <div class="subcontainer">
                <!-- <div v-if="newOpening.source">
                    {{ newOpening.source.sourceName}}
                </div> -->
                    <div class="container-row">
                        <label>Source</label>
                        <input>
                        <label>Page</label>
                        <input v-model="source.sourcePage">
                        <label>Variation</label>
                        <input v-model="source.subnumber">
                    </div>
                </div>
            </div>
            <button @click="submit">Submit</button>
        </form>
    </div>
</template>
<script>
export default {
    data() {
        return {
            newOpening: {},
            source: {},
            categories: []
        }
    },
    methods: {
        submit() {

        },

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
</style>