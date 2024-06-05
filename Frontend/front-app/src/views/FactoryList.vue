<template>
    <div>
        <h1>Chocolate Factories</h1>
        <div v-for="factory in sortedFactories" :key="factory.id" class="factory-card">
            <h2>{{ factory.name }}</h2>
            <img :src="getLogoPath(factory.logo)" alt="Logo" class="factory-logo">
            <p><strong>Location:</strong> {{ factory.location.street }}, {{ factory.location.city }}, {{ factory.location.state }}</p>
            <p><strong>Average Rating:</strong> {{ factory.averageRating }}</p>
            <p><strong>Working Hours:</strong> {{ factory.workingHours }}</p>
            <p><strong>Status:</strong> {{ factory.workStatus }}</p>
            <button @click="viewMore(factory.id)">View More</button>
            <button @click="addChocolate(factory.id)">Add Chocolate</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            factories: []
        }
    },
    mounted() {
        axios
            .get('http://localhost:8080/WebShopAppREST/rest/factories')
            .then(response => {
                this.factories = response.data;
            })
            .catch(error => {
                console.error("Error fetching factories", error);
            });
    },
    computed: {
        sortedFactories() {
            return this.factories.slice().sort((a, b) => {
                if (a.workStatus === 'Working' && b.workStatus !== 'Working') {
                    return -1;
                } else if (a.workStatus !== 'Working' && b.workStatus === 'Working') {
                    return 1;
                } else {
                    return 0;
                }
            });
        }
    },
    methods: {
        viewMore(factoryId) {
            this.$router.push({ name: 'FactoryDetails', params: { id: factoryId } });
        },
        addChocolate(factoryId) {
            this.$router.push({ name: 'AddChocolate', params: { id: factoryId } });
        },
        getLogoPath(logo) {
            try {
                return require(`../assets/${logo}`);
            } catch (e) {
                console.error("Error loading logo image", e);
                return '';  // Return a default or empty string if image not found
            }
        }
    }
}
</script>


<style>
.factory-card {
    border: 1px solid #ccc;
    padding: 16px;
    margin-bottom: 16px;
}
.factory-logo {
    width: 100px;
    height: auto;
}
</style>
