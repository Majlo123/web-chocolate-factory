import { createRouter, createWebHashHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import FactoryList from '../views/FactoryList.vue';
import FactoryDetails from '../views/FactoryDetails.vue';
import AddChocolate from '../views/AddChocolate.vue';
const routes = [
  {
    path: '/',
    name: 'FactoryList',
    component: FactoryList
},
{
    path: '/factories/:id',
    name: 'FactoryDetails',
    component: FactoryDetails
},
{
    path: '/factories/:id/add',
    name: 'AddChocolate',
    component: AddChocolate
}

];

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
