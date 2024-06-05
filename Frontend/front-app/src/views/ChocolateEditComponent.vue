<template>
    <div v-if="chocolate">
        <h2>Edit Chocolate</h2>
        <form @submit.prevent="updateChocolate">
            <div>
                <label for="name">Name:</label>
                <input type="text" v-model="chocolate.name" id="name" required />
            </div>
            <div>
                <label for="price">Price:</label>
                <input type="number" v-model="chocolate.price" id="price" required />
            </div>
            <div>
                <label for="chocolateKind">Kind:</label>
                <input type="text" v-model="chocolate.chocolateKind" id="chocolateKind" required />
            </div>
            <div>
                <label for="chocolateType">Type:</label>
                <input type="text" v-model="chocolate.chocolateType" id="chocolateType" required />
            </div>
            <div>
                <label for="weight">Weight:</label>
                <input type="number" v-model="chocolate.weight" id="weight" required />
            </div>
            <div>
                <label for="description">Description:</label>
                <textarea v-model="chocolate.description" id="description" required></textarea>
            </div>
            <button type="submit">Save</button>
            <button @click="cancelEdit">Cancel</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    props: ['chocolateId', 'factoryId'],
    data() {
        return {
            chocolate: null
        };
    },
    methods: {
        fetchChocolate() {
            axios.get(`http://localhost:8080/WebShopAppREST/rest/factories/${this.factoryId}/chocolates/${this.chocolateId}`)
                .then(response => {
                    this.chocolate = response.data;
                })
                .catch(error => {
                    console.error('Error fetching chocolate details', error);
                });
        },
        updateChocolate() {
            axios.put(`http://localhost:8080/WebShopAppREST/rest/factories/${this.factoryId}/chocolates/${this.chocolateId}`, this.chocolate)
                .then(() => {
                    this.$router.push({ name: 'FactoryDetails', params: { id: this.factoryId } });
                })
                .catch(error => {
                    console.error('Error updating chocolate', error);
                });
        },
        cancelEdit() {
            this.$router.push({ name: 'FactoryDetails', params: { id: this.factoryId } });
        }
    },
    mounted() {
        this.fetchChocolate();
    }
};
</script>
