import React from "react";
import ReactDOM from "react-dom";

import { BrowserRouter } from "react-router-dom";
import Main from "./Main";
import "bootstrap/dist/css/bootstrap.css";

import { Provider } from "react-redux";
import { createStore } from "redux";

import reducer from "./reducer";

const store = createStore(reducer);

ReactDOM.render(
  <Provider store={store}>
    <BrowserRouter>
      <Main />
    </BrowserRouter>
  </Provider>,
  document.getElementById("react")
);
