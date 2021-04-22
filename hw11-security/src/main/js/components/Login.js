import React, { Component } from "react";
import { Form } from "react-bootstrap";
import Button from "react-bootstrap/Button";
import { handleApiError, doFetch } from "../common/Tools";
import "bootstrap/dist/css/bootstrap.css";
import Alert from "react-bootstrap/Alert";

const params = new URLSearchParams(window.location.search);

class Login extends Component {
  constructor() {
    super();
    console.log("login");
    this.state = {
      login: null,
      password: null,
      alert: null,
    };
  }

  componentDidMount() {
    if (params.get("error") != null) {
      this.setLoginErrorWarning();
    }
  }

  handleChange = (field, value) => {
    const newState = {};
    newState[field] = value;
    console.log("newState:" + newState);
    this.setState(newState);
  };

  setLoginErrorWarning = () => {
    this.setState({ alert: "Login is failed" });
  };

  render() {
    const login = this.state.login;
    const password = this.state.password;

    const handleLogin = () => {
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
          console.log("v.redirected: " + response.redirected);
          if (response.redirected) {
            window.location = response.url;
          }
          // if (response.ok) {
          //   // switch to books
          //   props.history.push("/page/books");
          // }else{
          //   setLoginErrorWarning();
          // }
        })
        .catch((error) => {
          console.error("login error");
          //display error
          console.error(error);
        });
    };

    let warningMessage = "";
    if (this.state.alert) {
      warningMessage = (
        <Alert key="err" variant="warning">
          {this.state.alert}
        </Alert>
      );
    }

    return (
      <div style={{ width: "50%" }}>
        <h1>Login page</h1>
        {warningMessage}
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
