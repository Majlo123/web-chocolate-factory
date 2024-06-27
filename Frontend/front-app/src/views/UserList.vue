<template>
  <div v-if="user" class="container">
    <header class="header">
      <h1 class="main-title">Users list</h1>
      <router-link 
        v-if="user" 
        :to="{ name: 'FactoryListAdministrator', params: { username: user.username, factoryId: selectedFactoryId } }">Home</router-link>
      <router-link 
        v-if="user" 
        :to="{ name: 'AddFactory', params: { username: user.username } }">Add factory</router-link>
      <router-link 
        v-if="user" 
        :to="{ name: 'ViewProfileAdministrator', params: { username: user.username } }">View Profile</router-link>
        <router-link 
          v-if="user" 
          :to="{ name: 'UserList', params: { username: user.username} }">Users</router-link>
        <div class="auth"></div>
      <div class="auth">
        <span v-if="user">Welcome, {{ user.firstName }} {{ user.lastName }}!</span>
        <router-link v-else to="/sign-in">Sign In</router-link>
        <button v-if="user" @click="signOut">Sign Out</button>
      </div>
    </header>
      <div v-for="user in users" :key="user.username" class="factory-card">
        <p><strong>Username:</strong> {{ user.username }}</p>
        <p><strong>Password:</strong> {{ user.password }}</p>
        <p><strong>First Name:</strong> {{ user.firstName }}</p>
        <p><strong>Last Name:</strong> {{ user.lastName }}</p>
        <p><strong>Gender:</strong> {{ user.gender }}</p>
        <p><strong>Date of Birth:</strong> {{ user.birthDate }}</p>
        <p><strong>Role:</strong> {{ user.role }}</p>
      </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
  props: ['username'],
  data() {
    return {
      users: [],
      user: null,
    };
  },
  methods: {
    fetchUser() {
      axios.get(`http://localhost:8080/WebShopAppREST/rest/users`)
        .then(response => {
          this.user = response.data.find(user => user.username === this.$route.params.username);
        })
        .catch(error => {
          console.error('Error fetching user details', error);
        });
    },
    fetchFactory() {
      const factoryId = this.$route.params.factoryId;
      axios.get(`http://localhost:8080/WebShopAppREST/rest/factories/${factoryId}`)
        .then(response => {
          this.factory = response.data;
        })
        .catch(error => {
          console.error('Error fetching factory details', error);
        });
    },
    fetchUsers() {
        axios
          .get('http://localhost:8080/WebShopAppREST/rest/users')
          .then(response => {
            console.log('Fetched users:', response.data);
            this.users = response.data;
          })
          .catch(error => {
            console.error("Error fetching users", error);
          });
      },
    signOut() {
      this.$router.push({ name: 'FactoryList' });
    }
  },
  mounted() {
    this.fetchFactory();
    this.fetchUser();
    this.fetchUsers();
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
}
</style>
