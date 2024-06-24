<template>
    <div class="container">
      <div class="header">
        <h1 class="main-title">Chocolate Factories</h1>
        <router-link 
          v-if="user && selectedFactoryId" 
          :to="{ name: 'FactoryDetailsWorker', params: { username: user.username, factoryId: selectedFactoryId } }">
          Edit factory
        </router-link>
        <div class="auth">
          <span v-if="user">Welcome, {{ user.firstName }} {{ user.lastName }}!</span>
          <router-link v-else to="/sign-in">Sign In</router-link>
          <button v-if="user" @click="signOut">Sign Out</button>
        </div>
      </div>
      <div v-for="factory in sortedFactories" :key="factory.id" class="factory-card" @click="setSelectedFactory(factory.id)">
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
          <button @click.stop="viewMore(factory.id)" class="btn-primary">View More</button>
          <button @click.stop="addChocolate(factory.id)" class="btn-secondary">Add Chocolate</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    props: ['username', 'factoryId'],
    data() {
      return {
        factories: [],
        user: null,
        selectedFactoryId: this.factoryId || null
      };
    },
    mounted() {
      this.fetchUser();
      this.fetchFactories();
    },
    methods: {
      fetchUser() {
        axios.get(`http://localhost:8080/WebShopAppREST/rest/users`)
          .then(response => {
            this.user = response.data.find(user => user.username === this.username);
          })
          .catch(error => {
            console.error('Error fetching user details', error);
          });
      },
      fetchFactories() {
        axios.get('http://localhost:8080/WebShopAppREST/rest/factories')
          .then(response => {
            this.factories = response.data;
          })
          .catch(error => {
            console.error('Error fetching factories', error);
          });
      },
      viewMore(factoryId) {
        this.$router.push({ name: 'FactoryDetails', params: { id: factoryId } });
      },
      addChocolate(factoryId) {
        this.$router.push({ name: 'AddChocolate', params: { id: factoryId, username: this.username } });
      },
      getLogoPath(logo) {
        try {
          return require(`../assets/${logo}`);
        } catch (e) {
          console.error('Error loading logo image', e);
          return '';  
        }
      },
      signOut() {
        this.$router.push({ name: 'FactoryList' });
      },
      setSelectedFactory(factoryId) {
        this.selectedFactoryId = factoryId;
      }
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
    }
  };
  </script>
  
  <style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&display=swap');
  
  .container {
    font-family: 'Open Sans', sans-serif;
    background: #f9f9f9;
    padding: 20px;
  }
  
  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .main-title {
    font-size: 2.5rem;
    color: #333;
  }
  
  .auth {
    display: flex;
    align-items: center;
  }
  
  .auth span {
    margin-right: 20px;
    font-size: 1rem;
    color: #333;
  }
  
  .auth button {
    background-color: #6b3e26;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
  }
  
  .auth button:hover {
    background-color: #552e1a;
  }
  
  .factory-card {
    background: #fff;
    border: 1px solid #ddd;
    border-radius: 8px;
    padding: 20px;
    margin-bottom: 20px;
    cursor: pointer;
  }
  
  .factory-card:hover {
    background-color: #f1f1f1;
  }
  
  .factory-card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .factory-logo {
    width: 100px;
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
  