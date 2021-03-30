import React from "react";
import { Switch, Route, Router, BrowserRouter } from "react-router-dom";

import Root from "./pages/root";
import Devices from "./pages/devices";
import Rooms from "./pages/rooms";
import NotFound from "./pages/notfound";

import { useDispatch } from "react-redux";

const Main = () => {  
  return (
    <center>
      <a href="/">root</a>
      <br />
      <a href="/api">repo API</a>
      <br />
      <hr />
      <Switch>
        <Route exact path="/" component={Root} />
        <Route path="/page/rooms" component={Rooms} />
        <Route path="/page/devices" component={Devices} />
        <Route component={NotFound} />
      </Switch>
    </center>
  );
};

export default Main;
