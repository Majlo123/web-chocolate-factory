<template>
  <div>
    <table border="1">
      <thead>
        <tr>
          <th colspan="4">Lista knjiga izdavačke kuće {{ publisher }}</th>
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
        <td><a href="#" @click="goToPublisherPage(book.publisher)">{{ book.publisher }}</a></td>
        <td>{{ book.price }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      books: null,
      publisher: null
    };
  },
  mounted() {
    this.publisher = this.$route.params.publisher;
    axios
      .get('http://localhost:8080/WebShopAppREST/rest/books' + this.publisher)
      .then(response => {
        this.books = response.data;
      })
      .catch(error => {
        console.error('Error fetching books:', error);
      });
  },
  methods: {
    goToPublisherPage(publisher) {
      this.$router.push('/books/' + publisher);
    }
  }
};
</script>

<style scoped>
/* Ovde možete dodati stilizaciju koja se odnosi samo na ovu komponentu */
</style>
