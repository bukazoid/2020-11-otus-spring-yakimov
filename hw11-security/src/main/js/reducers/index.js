import { createStore, applyMiddleware, combineReducers } from "redux";
import reposReducer from "./reposReducer";

import { composeWithDevTools } from "redux-devtools-extension";
import thunk from "redux-thunk";

const rootReducer = combineReducers({
  repo: reposReducer,
});

export const store = createStore(rootReducer);
