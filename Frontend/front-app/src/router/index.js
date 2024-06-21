import { createRouter, createWebHashHistory } from 'vue-router';
import FactoryList from '../views/FactoryList.vue';
import FactoryDetails from '../views/FactoryDetails.vue';
import AddChocolate from '../views/AddChocolate.vue';
import ChocolateEditComponent from '../views/ChocolateEditComponent.vue';
import SignIn from '../views/SignIn.vue';
import SignUp from '../views/SignUp.vue';
import FactoryListManager from '../views/FactoryListBuyer.vue';
import FactoryDetailsManager from '../views/FactoryDetailsManager.vue';

const routes = [
  {
    path: '/',
    name: 'FactoryList',
    component: FactoryList
  },
  {
    path: '/sign-in',
    name: 'SignIn',
    component: SignIn
  },
  {
    path: '/sign-up',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/factories/:id',
    name: 'FactoryDetails',
    component: FactoryDetails
  },
  {
    path: '/factories/:factoryId/add/:username',
    name: 'AddChocolate',
    component: AddChocolate,
    props: true
  },
  {
    path: '/factories/:factoryId/users/:username/chocolates/:chocolateId/edit',
    name: 'EditChocolate',
    component: ChocolateEditComponent,
    props: true
  },
  {
    path: '/factory-list-manager/:username',
    name: 'FactoryListManager',
    component: FactoryListManager,
    props: true
  },
  {
    path: '/factory-details-manager/:username/factories/:factoryId',
    name: 'FactoryDetailsManager',
    component: FactoryDetailsManager,
    props: true
  }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes
});

export default router;
