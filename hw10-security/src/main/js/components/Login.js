import React, { Component } from "react";
import { Form } from "react-bootstrap";
import Button from "react-bootstrap/Button";
import { handleApiError, doFetch } from "../common/Tools";

import "bootstrap/dist/css/bootstrap.css";

class Login extends Component {
  constructor() {
    super();
    this.state = {
      login: null,
      password: null,
    };
  }

  handleChange = (field, value) => {
    const newState = {};
    newState[field] = value;
    console.log("newState:" + newState);
    this.setState(newState);
  };

  render() {
    const login = this.state.login;
    const password = this.state.password;

    const handleLogin = () => {
      console.log("the login: " + login);

      const data = new FormData();
      data.append("username", login);
      data.append("password", password);

	  const props = this.props;
      doFetch(
        "/perform_login",
        "POST",
        "username=" + login + "&password=" + password,
        "application/x-www-form-urlencoded"
      )
        .then((response) => {
		  if (response.status == 0){
			// switch to books
			props.history.push("/page/books");
		  }
        })
        .catch((error) => {
          //display error
          console.error(error);
          // reload data
          this.loadData();
        });
    };

    return (
      <div style={{ width: "50%" }}>
        <h1>Login page</h1>
        <Form>
          <Form.Group controlId="login">
            <Form.Label>login</Form.Label>
            <Form.Control
              type="text"
              name="login"
              placeholder="login"
              defaultValue={login}
              onChange={(e) => this.handleChange(e.target.name, e.target.value)}
            />
            <Form.Text className="text-muted">Login</Form.Text>
          </Form.Group>

          <Form.Group controlId="password">
            <Form.Label>password</Form.Label>
            <Form.Control
              type="password"
              name="password"
              placeholder="password"
              defaultValue={password}
              onChange={(e) => this.handleChange(e.target.name, e.target.value)}
            />
            <Form.Text className="text-muted">Password</Form.Text>
          </Form.Group>
        </Form>
        <Button variant="primary" onClick={handleLogin}>
          Login
        </Button>
      </div>
    );
  }
}

export default Login;
