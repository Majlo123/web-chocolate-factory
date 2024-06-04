
<template>
  <div>
    <table border="1">
      <thead>
        <tr>
          <th colspan="4">Lista knjiga</th>
        </tr>
        <tr>
          <th>Naziv knjige</th>
          <th>Naziv autora</th>
          <th>Naziv izdavačke kuće</th>
          <th>Cena</th>
        </tr>
      </thead>
      <tr v-for="book in books" :key="book.id">
        <td>{{ book.name }}</td>
        <td>{{ book.author }}</td>
        <td><a href="#" @click.prevent="goToPublisherPage(book.publisher)">{{ book.publisher }}</a></td>
        <td>{{ book.price }}</td>
      </tr>
    </table>
    <br>
    <form @submit.prevent="deleteBook">
      <label>Naziv:</label>
      <input type="text" v-model="deleteBookName">
      <br>
      <input type="submit" value="Obriši">
    </form>
    <p>{{ deleteBookMessage }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      books: null,
      deleteBookName: null,
      deleteBookMessage: null
    };
  },
  mounted() {
  axios
    .get('http://localhost:8080/WebShopAppREST/rest/books')
    .then(response => {
      this.books = response.data;
      console.log(this.books); 
    })
    .catch(error => {
      console.error('Error fetching books:', error);
    });
},

  methods: {
    goToPublisherPage(publisher) {
  axios
    .get('http://localhost:8080/WebShopAppREST/rest/books/'+publisher)
    .then(response => {
      this.books = response.data;
      console.log(this.books); 
    })
    .catch(error => {
      console.error('Error fetching books:', error);
    });
},

    deleteBook() {
      axios
        .delete('http://localhost:8080/WebShopAppREST/rest/books/' + this.deleteBookName)
        .then(() => {
          this.deleteBookMessage = '';
          axios
            .get('http://localhost:8080/WebShopAppREST/rest/books')
            .then(response => {
              this.books = response.data;
            })
            .catch(error => {
              console.error('Error fetching books after deletion:', error);
            });
        })
        .catch(error => {
          if (error.response && error.response.status === 400) {
            this.deleteBookMessage = 'Knjiga sa unetim nazivom ne postoji!';
          } else {
            console.error('Error deleting book:', error);
          }
        });
    }
  }
};
</script>
