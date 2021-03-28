import React, { Component } from "react";
import Grid from "../common/Grid";
import GridProperty from "../common/GridProperty";
import { handleApiError, doFetch } from "../common/Tools";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import { Form } from "react-bootstrap";

class Authors extends Component {
  constructor() {
    super();
    this.state = {
      data: null,
      editObject: null,
    };
  }

  /**
   * model
   */
  model = Authors.getModel("Authors"/*BTW: it is for localization*/, ["name", "actions"]);

  static getModel = (msgPrefix, idList) => {
    return idList.map((id) => new GridProperty(id, id));
  };

  componentDidMount() {
    this.loadData();
  }

  componentDidUpdate(prevProps) {
    if (this.state.refreshList) {
      this.loadData();
    }
  }

  onSave = (author) => {
    const id = author.id;
    let method = "POST";
    let url = "/api/authors";
    if (id) {
      method = "PUT";
      url += "/" + id;
    }
    doFetch(url, method, JSON.stringify(author))
      .then(handleApiError)
      .then(() => this.loadData())
      .catch((error) => {
        //display error
        console.error(error);
        // reload data
        this.loadData();
      });
  };

  handleChange = (field, value) => {
    const newObj = Object.assign({}, this.state.editObject);
    newObj.name = value;

    this.setState({
      editObject: newObj,
    });
  };

  genEditDialog = () => {
    const obj = this.state.editObject;
    const editFlag = !!obj;
    if (!editFlag) {
      return "";
    }

    const handleClose = () => {
      this.setState({ editObject: null });
    };

    const handleSave = () => {
      this.onSave(this.state.editObject);
    };

    return (
      <Modal show={editFlag} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Edit</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form.Group controlId="name">
            <Form.Label>Name</Form.Label>
            <Form.Control
              type="text"
              name="name"
              placeholder="Name"
              defaultValue={obj.name}
              onChange={(e) => this.handleChange(e.target.name, e.target.value)}
            />
            <Form.Text className="text-muted">Author's name</Form.Text>
          </Form.Group>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Cancel
          </Button>
          <Button variant="primary" onClick={handleSave}>
            Save
          </Button>
        </Modal.Footer>
      </Modal>
    );
  };

  loadData = () => {
    doFetch("/api/authors", "GET")
      .then(handleApiError)
      .then((response) => response.json())
      .then((json) => {
        this.setState({
          data: json,
          editObject: null,
        });
      })
      .catch((error) => console.error(error));
  };

  prepareRows = (data) => {
    return data.map((author) => {
      const row = Object.assign({}, author);

      const onEditAuthor = () => {
        this.setState({ editObject: author });
      };

      // no delete for authors, don't wanna control relations now
      row["actions"] = (
        <span>
          <Button variant="primary" onClick={onEditAuthor}>
            Edit
          </Button>
        </span>
      );

      return row;
    });
  };

  render() {
    if (!this.state.data) {
      return "";
    }

    const handleCreate = () => {
      this.setState({
        deleteObject: null,
        editObject: { genres: [], author: {} },
      });
    };

    const { data } = this.state;
    const rows = this.prepareRows(data);
    return (
      <div>
        <h1>Authors</h1>
        <Grid model={this.model} data={rows} />
        <Button variant="primary" onClick={handleCreate}>
          Create
        </Button>
        {this.genEditDialog()}
      </div>
    );
  }
}

export default Authors;
