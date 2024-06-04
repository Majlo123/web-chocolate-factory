import { createRouter, createWebHashHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import Books from '../views/Books.vue';
import AddBook from '../views/AddBook.vue';
import Flights from '../views/Flights.vue';
import AddFlight from '../views/AddFlight.vue';
const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/books",
    component:Books
  },
  {
    path:"/add_book",
    component:AddBook
  },
  {
    path:"/flights",
    component:Flights
  },
  {
    path:"/add_flight",
    component:AddFlight
  }

];

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
