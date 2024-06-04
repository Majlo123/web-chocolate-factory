<template>
    <div>
        <table border="1">
            <thead>
                <tr>
                    <th colspan="5">Lista letova</th>
                </tr>
            </thead>
            <tr>
                <th>Sifra Leta</th>
                <th>Polaziste</th>
                <th>Odrediste</th>
                <th>Trajanje</th>
                <th>Cena</th>
            </tr>
            <tr v-for="flight in flights" :key="flight.id">
                <th>{{flight.sifra}}</th>
                <th>{{flight.polaziste}}</th>
                <th>{{flight.odrediste}}</th>
                <th>{{flight.trajanje}}</th>
                <th>{{flight.cena}}</th>
            </tr>
        </table>
        <br>
        <br>
            <tr>
                <label>Polaziste:</label>
                <select v-model="polazisteSearch">
                    <option value="Barselona" selected>Barselona</option>
                    <option value="Pariz">Pariz</option>
                    <option value="Istanbul">Istanbul</option>
                </select>
            </tr>
            <p>{{errorMessage}}</p>
            <tr>
                <label>Cena od</label>
                <input type="number" v-model="minCena">
                <label>Cena do</label>
                <input type="number" v-model="maxCena">
            </tr>
            <tr>
                <input type="submit" @click.prevent="getBy(polazisteSearch,minCena,maxCena)" value="Pretrazi">
                <input type="submit" @click.prevent="reset()" value="Ponisti pretragu">
            </tr>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return{
            polazisteSearch:null,
            minCena:null,
            maxCena:null,
            flights:null,
            errorMessage:null
        }
    },
    mounted(){
        axios
        .get('http://localhost:8080/WebShopAppREST/rest/flights')
        .then(response=>{
            this.flights=response.data
            console.log(response)
        })
        .catch(error=>{
            console.error("Error fatching flights",error)
        })
    },
    methods:{
        getBy(polazisteSearch,minCena,maxCena){
            axios
             .get('http://localhost:8080/WebShopAppREST/rest/flights/'+polazisteSearch+'/'+minCena+'/'+maxCena)
        .then(response=>{
            this.flights=response.data
            console.log(response)
            if(this.flights.length===0){
                this.errorMessage='Ne postoji ni jedan let!'
            }
        })
        .catch(error=>{
            console.error("Error fatching flights",error)
        })
           
        },
    reset(){
        this.polazisteSearch='';
        this.minCena='';
        this.maxCena='';
        this.errorMessage='';
        axios
        .get('http://localhost:8080/WebShopAppREST/rest/flights')
        .then(response=>{
            this.flights=response.data
            console.log(response)
        })
        .catch(error=>{
            console.error("Error fatching flights",error)
        })
    }
    }
}
</script>