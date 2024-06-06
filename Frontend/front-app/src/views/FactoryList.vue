<template>
    <div class="container">
        <h1 class="main-title">Chocolate Factories</h1>
        <div v-for="factory in sortedFactories" :key="factory.id" class="factory-card">
            <div class="factory-card-header">
                <h2>{{ factory.name }}</h2>
                <img :src="getLogoPath(factory.logo)" alt="Logo" class="factory-logo">
            </div>
            <p><strong>Location:</strong> {{ factory.location.street }}, {{ factory.location.city }}, {{ factory.location.state }}</p>
            <p><strong>Average Rating:</strong> {{ factory.averageRating }}</p>
            <p><strong>Working Hours:</strong> {{ factory.workingHours }}</p>
            <p><strong>Status:</strong> {{ factory.workStatus }}</p>
            <p><strong>Comments:</strong> {{ factory.comments }}</p>
            <div class="factory-card-buttons">
                <button @click="viewMore(factory.id)" class="btn-primary">View More</button>
                <button @click="addChocolate(factory.id)" class="btn-secondary">Add Chocolate</button>
            </div>
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

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&display=swap');

.container {
    font-family: 'Open Sans', sans-serif;
    background: #f9f9f9;
    padding: 20px;
}

.main-title {
    text-align: center;
    font-size: 2.5rem;
    color: #333;
    margin-bottom: 20px;
}

.factory-card {
    background: #fff;
    border: 1px solid #ddd;
    border-radius: 8px;
    padding: 20px;
    margin-bottom: 20px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    transition: transform 0.2s;
}

.factory-card:hover {
    transform: scale(1.02);
}

.factory-card-header {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.factory-logo {
    width: 80px;
    height: auto;
}

.factory-card-buttons {
    display: flex;
    justify-content: space-between;
    margin-top: 10px;
}

.btn-primary, .btn-secondary {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1rem;
}

.btn-primary {
    background-color: #6b3e26;
    color: white;
    margin-right: 10px;
}

.btn-secondary {
    background-color: #ddd;
    color: #333;
}

.btn-primary:hover {
    background-color: #552e1a;
}

.btn-secondary:hover {
    background-color: #ccc;
}
</style>
