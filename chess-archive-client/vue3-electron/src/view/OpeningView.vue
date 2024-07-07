<template>
    <div>
        opening view
        <!-- <div v-id="$store.state.openings" v-for="opening in $store.state.openings" :key="opening.openingId">
            <p>{{opening.opening_id}}</p>
        </div> -->
        {{ $store.state.openings }}
    </div>
</template>
<script>
export default {
    
}
</script>
<style>
    
</style>