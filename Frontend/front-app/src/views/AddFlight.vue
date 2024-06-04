<template>
    <div>
        <form>
            <tr>
                <th>Sifra leta:</th>
                <th><input type="text" v-model="flight.sifra" :style="sifraStyle"></th>
            </tr>
             <tr>
                <th>Polaziste:</th>
                <th><input type="text" v-model="flight.polaziste" :style="polazisteStyle"></th>
            </tr>
             <tr>
                <th>Odrediste:</th>
                <th><input type="text" v-model="flight.odrediste" :style="odredisteStyle"></th>
            </tr>
             <tr>
                <th>Trajanje:</th>
                <th><input type="number" v-model="flight.trajanje" :style="trajanjeStyle"></th>
            </tr>
             <tr>
                <th>Cena:</th>
                <th><input type="number" v-model="flight.cena" :style="cenaStyle"></th>
            </tr>
            <tr>
                <th><input type="submit" @click.prevent="dodaj" value="Dodaj"></th>
            </tr>
            <p>{{errorMessage}}</p>
        </form>
    </div>
</template>
<script>
import axios from 'axios';
export default {
    data() {
        return{
            flight:{sifra:null,polaziste:null,odrediste:null,trajanje:null,cena:null},
            sifraStyle:null,
            polazisteStyle:null,
            odredisteStyle:null,
            trajanjeStyle:null,
            cenaStyle:null,
            errorMessage:null
        }
    },
    methods:{
        dodaj(){
            this.resetStyles();
            if(!this.flight.sifra){
                this.sifraStyle='background-color :red';
                this.errorMessage='Nisu uneti svi podaci!'
            }
            if(!this.flight.odrediste){
                this.odredisteStyle='background-color :red';
                this.errorMessage='Nisu uneti svi podaci!'
            }
            if(!this.flight.polaziste){
                this.polazisteStyle='background-color :red';
                this.errorMessage='Nisu uneti svi podaci!'
            }
            if(this.flight.trajanje<0||this.flight.trajanje===null){
                this.trajanjeStyle='background-color :red';
                this.errorMessage='Nisu uneti svi podaci!'
            }
            if(this.flight.cena<0||this.flight.cena===null){
                this.cenaStyle='background-color :red';
                this.errorMessage='Nisu uneti svi podaci!'
            }
            axios
            .post('http://localhost:8080/WebShopAppREST/rest/flights',this.flight)
            .then(response=>{
                console.log("Uspesno napravljen let!",response)
                this.$router.push('/flights')
            })
            .catch(error=>{
                console.error("Error adding flight",error)
            })

        },
        resetStyles(){
            this.sifraStyle='',
            this.odredisteStyle='',
            this.trajanjeStyle='',
            this.cenaStyle=''
        }
    }
}
</script>