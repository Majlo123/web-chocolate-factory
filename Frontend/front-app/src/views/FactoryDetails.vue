<template>
      <div v-if="factory">
        <button @click="$router.back()">Back to Factories</button>
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
            <p><strong>Type:</strong> {{ chocolate.chocolate_kind }} - {{ chocolate.chocolate_type }}</p>
            <p><strong>Weight:</strong> {{ chocolate.weight }} g</p>
            <p><strong>Description:</strong> {{ chocolate.description }}</p>
            <img :src="'/path/to/images/' + chocolate.image" alt="Chocolate Image" class="chocolate-image">
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
    viewMore() {
      this.$router.push(`/factories/${this.$route.params.id}/chocolates`);
    }
  },
  mounted() {
    this.fetchFactory();
  }
};
</script>

<style scoped>
/* Add your styles here */
</style>
