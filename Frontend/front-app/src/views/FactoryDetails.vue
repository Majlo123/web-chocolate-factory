<template>
    <div v-if="factory">
        <button @click="this.$router.push({ name: 'FactoryList'})">Back to Factories</button>
        <h1>{{ factory.name }}</h1>
        <img :src="'/path/to/images/' + factory.logo" alt="Logo" class="factory-logo">
        <p><strong>Location:</strong> {{ factory.location.street }}, {{ factory.location.city }}, {{ factory.location.state }}</p>
        <p><strong>Average Rating:</strong> {{ factory.averageRating }}</p>
        <p><strong>Working Hours:</strong> {{ factory.workingHours }}</p>
        <p><strong>Status:</strong> {{ factory.workStatus }}</p>
        <h2>Chocolates</h2>
        <div v-for="chocolate in factory.chocolates" :key="chocolate.id" class="chocolate-card">
            <p><strong>Name:</strong> {{ chocolate.name }}</p>
            <p><strong>Price:</strong> {{ chocolate.price }} RSD</p>
            <p><strong>Type:</strong> {{ chocolate.chocolateKind }} - {{ chocolate.chocolateType }}</p>
            <p><strong>Weight:</strong> {{ chocolate.weight }} g</p>
            <p><strong>Description:</strong> {{ chocolate.description }}</p>
            <img :src="'/path/to/images/' + chocolate.image" alt="Chocolate Image" class="chocolate-image">
            <button @click="deleteChocolate(chocolate.id)">Delete Chocolate</button>
        </div>
    </div>
</template>


<script>
import axios from 'axios';

export default {
    data() {
        return {
            factory: null
        };
    },
    methods: {
        fetchFactory() {
            const id = this.$route.params.id;
            axios
                .get(`http://localhost:8080/WebShopAppREST/rest/factories/${id}`)
                .then(response => {
                    this.factory = response.data;
                })
                .catch(error => {
                    console.error('Error fetching factory details', error);
                });
        },
        deleteChocolate(chocolateId) {
            const factoryId = this.$route.params.id;
            axios
                .delete(`http://localhost:8080/WebShopAppREST/rest/factories/${factoryId}/${chocolateId}`)
                .then(() => {
                    this.factory.chocolates = this.factory.chocolates.filter(chocolate => chocolate.id !== chocolateId);
                })
                .catch(error => {
                    console.error('Error deleting chocolate', error);
                });
        }
    },
    mounted() {
        this.fetchFactory();
    }
};

</script>

<style scoped>
.factory-logo {
    width: 100px;
    height: auto;
}

.chocolate-card {
    border: 1px solid #ccc;
    padding: 10px;
    margin: 10px 0;
}

.chocolate-image {
    width: 100px;
    height: auto;
}
</style>
