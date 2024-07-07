import { createStore as _createStore } from 'vuex';
// import axios from 'axios';
// import OpeningService from '../service/OpeningService';
// import CategoryService from '../service/CategoryService';
// import SourceService from '../service/SourceService';


export function createStore() {
  let store = _createStore({
    state: {
      openings: []
    },
    mutations: {
      LOAD_OPENINGS(state, payload) {
        state.openings = payload;
      }
    },
  });
  return store;
}
