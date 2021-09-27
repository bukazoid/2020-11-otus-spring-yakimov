import React from "react";
import Alert from "react-bootstrap/Alert";

export default class MyAlert extends React.Component {

  
  render() {

    let warningMessage = "";
    if (this.store.alert) {
      warningMessage = (
        <Alert key="err" variant="warning">
          {this.state.alert}
        </Alert>
      );
    }
    return { warningMessage };
  }
}
