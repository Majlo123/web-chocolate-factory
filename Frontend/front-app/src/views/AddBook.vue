<template>
  <div>
    <form>
    <table>
      <tr>
        <td>Naziv knjige</td>
        <td><input type="text" v-model="book.name" :style="nameStyle"></td>
      </tr>
      <tr>
        <td>Naziv autora</td>
        <td><input type="text" v-model="book.author" :style="authorStyle"></td>
      </tr>
      <tr>
        <td>Naziv izdavacke kuce</td>
        <td><select v-model="book.publisher" :style="publisherStyle">
          <option value="Laguna" selected>Laguna</option>
          <option value="Vulkan">Vulkan</option>
          <option value="Logos">Logos</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>Cena</td>
        <td><input type="number" v-model="book.price" :style="numberStyle"></td>
      </tr>
      <tr>
        <td><input type="submit" @click.prevent="addBook" value="Dodaj"></td>
       
      </tr>
       <p>{{errorMessage}}</p>
    </table>
    </form>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  data() {
    return{
      book:{name:null,author:null,publisher:null,price:null},
      nameStyle:'',
      authorStyle:'',
      publisherStyle:'',
      numberStyle:'',
      errorMessage:''
    }
  },
 methods:{
  addBook() {
        this.resetStyles();
        this.errorMessage = '';
        
        // Validate inputs
        if (!this.book.name) {
            this.nameStyle = 'background-color: red';
            this.errorMessage = 'Sva polja forme su obavezna!';
        }
        if (!this.book.author) {
            this.authorStyle = 'background-color: red';
            this.errorMessage = 'Sva polja forme su obavezna!';
        }
        if (this.book.price === null || this.book.price < 0) {
            this.numberStyle = 'background-color: red';
            this.errorMessage = 'Sva polja forme su obavezna!';
        }
        if (!this.book.publisher) {
            this.publisherStyle = 'background-color: red';
            this.errorMessage = 'Sva polja forme su obavezna!';
        }

        if (this.errorMessage) return;
    axios
    .post('http://localhost:8080/WebShopAppREST/rest/books',this.book)
    .then(response=>{
      console.log('Knjiga uspesno napravljena',response.data);
      this.$router.push('/books')
    })
    .catch(error=>{
      if(error.response&&error.response.status===400){
         this.errorMessage='Nisu sva polja populjena!'
      }
      console.error('Error adding book',error);
    })


  },
  resetStyles(){
      this.nameStyle='',
      this.authorStyle='',
      this.publisherStyle='',
      this.numberStyle='',
      this.errorMessage=''
  }
  }

 };
</script>
