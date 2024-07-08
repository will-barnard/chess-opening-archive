import { createStore as _createStore } from 'vuex';
import OpeningService from '../service/OpeningService';
import CategoryService from '@/service/CategoryService';
import SourceService from '../service/SourceService';


export function createStore() {
  let store = _createStore({
    state: {
      openings: [],
      categories: [],
      sources: []
    },
    mutations: {
      LOAD_OPENINGS(state) {
        OpeningService.getAllOpenings().then( 
          (response) => {
            state.openings = response.data;
          })
      },
      CREATE_OPENING(state, payload) {
        OpeningService.createOpening(payload).then(
          (response) => {
            state.openings.push(response.data)
          }
        )
      },
      UPDATE_OPENING(state, payload) {
        OpeningService.updateOpening(payload).then(
          (response) => {
            state.openings = state.openings.filter(
              (opening) => {
                return opening.openingId != payload.openingId;
              }
            ).concat(response.data);
          }
        )
      },
      DELETE_OPENING(state, payload) {
        OpeningService.deleteOpening(payload).then(
          () => {
            state.openings = state.openings.filter(
              (opening) => {
                return opening.openingId != payload;
              }
            )
          }
        )
      },
      LOAD_CATEGORIES(state) {
        CategoryService.getAllCategories().then(
          (response) => {
            state.categories = response.data;
          }
        )
      },
      CREATE_CATEGORY(state, payload) {
        state.categories.push(payload);
      },
      UPDATE_CATEGORY(state, payload) {
        CategoryService.updateCategory(payload).then(
          (response) => {
            state.categories = state.categories.filter(
              (category) => {
                return category.categoryId != payload.categoryId;
              }
            ).concat(response.data);
          }
        )
      },
      DELETE_CATEGORY(state, payload) {
        CategoryService.deleteCategory(payload).then(
          () => {
            state.categories = state.categories.filter(
              (category) => {
                return category.categoryId != payload;
              }
            )
          }
        )
      },
      LOAD_SOURCES(state) {
        SourceService.getAllSources().then(
          (response) => {
            state.sources = response.data;
          }
        )
      },
      CREATE_SOURCE(state, payload) {
        SourceService.createSource(payload).then(
          (response) => {
            state.sources.push(response.data)
          }
        )
      },
      UPDATE_SOURCE(state, payload) {
        SourceService.updateSource(payload).then(
          (response) => {
            state.sources = state.sources.filter(
              (source) => {
                return source.sourceId != payload.sourceId;
              }
            ).concat(response.data);
          }
        )
      },
      DELETE_SOURCE(state, payload) {
        SourceService.deleteSource(payload).then(
          () => {
            state.sources = state.sources.filter(
              (source) => {
                return source.sourceId != payload;
              }
            )
          }
        )
      }
    },
  });
  return store;
}
