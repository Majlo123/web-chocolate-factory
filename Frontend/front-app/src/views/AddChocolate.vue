<template>
    <div>
        <button @click="$router.back()">Back to Factories</button>
        <h1>Add Chocolate to {{ factoryName }}</h1>
        <form @submit.prevent="addChocolate">
            <div>
                <label>Name:</label>
                <input type="text" v-model="newChocolate.name">
            </div>
            <div>
                <label>Price:</label>
                <input type="number" v-model="newChocolate.price">
            </div>
            <div>
                <label>Chocolate Kind:</label>
                <select v-model="newChocolate.chocolate_kind">
                    <option value="White">White</option>
                    <option value="Dark">Dark</option>
                    <option value="Milk">Milk</option>
                </select>
            </div>
            <div>
                <label>Chocolate Type:</label>
                <input type="text" v-model="newChocolate.chocolate_type">
            </div>
            <div>
                <label>Weight:</label>
                <input type="number" v-model="newChocolate.weight">
            </div>
            <div>
                <label>Description:</label>
                <textarea v-model="newChocolate.description"></textarea>
            </div>
            <div>
                <label>Image:</label>
                <input type="text" v-model="newChocolate.image">
            </div>
            <button type="submit">Add Chocolate</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            factoryName: '',
            newChocolate: {
                name: '',
                price: 0,
                chocolate_kind: 'White',
                chocolate_type: '',
                weight: 0,
                description: '',
                image: ''
            }
        }
    },
    mounted() {
        const factoryId = this.$route.params.id;
        axios
            .get(`http://localhost:8080/WebShopAppREST/rest/factories/${factoryId}`)
            .then(response => {
                this.factoryName = response.data.name;
            })
            .catch(error => {
                console.error("Error fetching factory details", error);
            });
    },
    methods: {
        addChocolate() {
            const factoryId = this.$route.params.id;
            axios
                .post(`http://localhost:8080/WebShopAppREST/rest/factories/${factoryId}/chocolates`, this.newChocolate)
                .then(response => {
                    alert('Chocolate added successfully');
                    this.$router.push({ name: 'FactoryDetails', params: { id: factoryId } });
                })
                .catch(error => {
                    console.error("Error adding chocolate", error);
                });
        }
    }
}
</script>

<style>
</style>
