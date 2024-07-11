<template>
    <div>
        <div>
            <div v-if="$store.state.openings.length > 0">
                <div  v-for="opening in $store.state.openings.filter(
                    (opening) => {
                        return sourceId = opening.source.sourceId && sourcePage == opening.source.sourcePage;
                    }
                )" :key="opening.openingId">
                    
                    <OpeningCard :opening="opening" />

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
            sourceId: 0,
            sourcePage: 0
        }
    },
    created() {
        if (this.$route.params.sourceId) {
            if (this.$route.params.sourcePage) {
                this.sourceId = this.$route.params.sourceId;
                this.sourcePage = this.$route.params.sourcePage;
            }
        }
    }
}
</script>

<style>
    
</style>