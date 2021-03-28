import React, { Component } from "react";
import Grid from "../common/Grid";
import GridProperty from "../common/GridProperty";
import { handleApiError, doFetch } from "../common/Tools";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import { Form } from "react-bootstrap";
import Alert from "react-bootstrap/Alert";

class Books extends Component {
  constructor() {
    super();
    this.state = {
      data: null,
      editObject: null,
      deleteObject: null,
    };
  }

  /**
   * model
   */

  static getModel = (msgPrefix, idList) => {
    return idList.map((id) => new GridProperty(id, id));
  };
  model = Books.getModel("Book", ["title", "author", "genres", "actions"]);

  componentDidMount() {
    this.loadData();
  }

  componentDidUpdate(prevProps) {
    if (this.state.refreshList) {
      this.loadData();
    }
  }

  onDelete = (book) => {
    const id = book.id;
    doFetch("/api/books/" + id, "DELETE", id)
      .then(handleApiError)
      .then(() => this.loadData())
      .catch((error) => {
        //display error
        error.then((text) => {
          console.error("error message: " + text);
          if (text == "Error.forbidden") {
            // need a way to not do it in every class, redux + own alert component?
            this.setState({
              alert: "You have no rights to delete books!",
            });
          } else {
            this.setState({ alert: text });
          }
        });

        // reload data
        this.loadData();
      });
  };

  onSave = (book) => {
    console.log("store: " + this.props.store);
    const id = book.id;
    let method = "POST";
    let url = "/api/books";
    if (id) {
      method = "PUT";
      url += "/" + id;
    }
    doFetch(url, method, JSON.stringify(book))
      .then(handleApiError)
      .then(() => this.loadData())
      .catch((error) => {
        //display error
        error.then((text) => {
          console.error("error message: " + text);
          if (text == "Error.forbidden") {
            //this.props.store.dispatch({type: 'alert',value: 'You have no rights to modify/create books!'}); //not workable yet
            // need a way to not do it in every class, redux + own alert component?
            this.setState({
              alert: "You have no rights to modify/create books!",
            });
          } else {
            this.setState({ alert: text });
          }
        });

        // reload data
        this.loadData();
      });
  };

  confirmDelete = (book) => {};

  loadData = () => {
    console.log("Books: loadData");
    doFetch("/api/books", "GET")
      .then(handleApiError)
      .then((response) => response.json())
      .then((json) => {
        this.setState({
          data: json,
          refreshList: false,
          deleteObject: null,
          editObject: null,
          alert: null,
        });
        this.loadGenresAndAuthors();
      })
      .catch((err) => {
        err.then((text) => {
          console.error("err: " + text);
        });
      });
  };

  loadAuthors = () => {
    doFetch("/api/authors", "GET")
      .then(handleApiError)
      .then((response) => response.json())
      .then((json) =>
        this.setState({
          authors: json,
        })
      )
      .catch((error) => console.error(error));
  };

  loadGenres = () => {
    doFetch("/api/genres", "GET")
      .then(handleApiError)
      .then((response) => response.json())
      .then((json) =>
        this.setState({
          genres: json,
        })
      )
      .catch((error) => console.error(error));
  };

  handleChange = (field, value) => {
    // yep, it should be done another way,but i can't handle select and multiselect for now
    console.log(field + " = " + value);
    const newObj = Object.assign({}, this.state.editObject);

    if (field == "title") {
      newObj.title = value;
    } else if (field == "author") {
      const newAuthor = this.state.authors.find((a) => a.id == value);
      newObj.author = newAuthor;
    } else if (field == "genres") {
      const genres = [];
      console.log("length: " + value.length);
      for (let i = 0; i < value.length; i++) {
        const genreId = value.item(i).value;
        const genre = this.state.genres.find((g) => g.id == genreId);
        genres.push(genre);
      }
      newObj.genres = genres;
    }

    console.log("newObj: " + JSON.stringify(newObj));

    this.setState({
      editObject: newObj,
    });
  };

  loadGenresAndAuthors = () => {
    // possible problem is that we don't have momemt when it is loaded
    this.loadAuthors();
    this.loadGenres();
  };

  prepareRows = (data) => {
    return data.map((book) => {
      const rowBook = Object.assign({}, book);
      rowBook["author"] = book.author?.name || "";
      let genres = book.genres;
      if (genres) {
        genres = genres.map((genre) => {
          return genre.name + " ";
        });
      }
      rowBook["genres"] = genres;

      const onDeleteBook = () => {
        this.setState({ deleteObject: book });
      };

      const onEditBook = () => {
        this.setState({ editObject: book });
      };

      rowBook["actions"] = (
        <span>
          <Button variant="primary" onClick={onEditBook}>
            Edit
          </Button>
          &nbsp;
          <Button variant="primary" onClick={onDeleteBook}>
            Delete
          </Button>
        </span>
      );

      return rowBook;
    });
  };

  genEditDialog = () => {
    const obj = this.state.editObject;
    const editFlag = !!obj;
    if (!editFlag) {
      return "";
    }

    const handleClose = () => {
      this.setState({ deleteObject: null, editObject: null });
    };

    const handleSave = () => {
      this.onSave(this.state.editObject);
    };

    const editObj = this.state.editObject;

    // generate options
    let authors = [];
    this.state.authors.forEach(function (item) {
      authors.push(<option value={item.id}>{item.name}</option>);
    });

    let genres = [];
    this.state.genres.forEach(function (item) {
      genres.push(
        <option key={item.id} value={item.id}>
          {item.name}
        </option>
      );
    });

    const selectedGenres = editObj.genres.map((genre) => {
      return genre.id;
    });

    return (
      <Modal show={editFlag} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Edit</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form.Group controlId="title">
            <Form.Label>Title</Form.Label>
            <Form.Control
              type="text"
              name="title"
              placeholder="Book title"
              defaultValue={obj.title}
              onChange={(e) => this.handleChange(e.target.name, e.target.value)}
            />
            <Form.Text className="text-muted">Book's title</Form.Text>
          </Form.Group>
          <Form.Group controlId="author">
            <Form.Label>Author</Form.Label>
            <Form.Control
              as="select"
              name="author"
              value={this.state.editObject.author.id}
              onChange={(e) => this.handleChange("author", e.target.value)}
              style={this.props.style}
            >
              {authors}
            </Form.Control>
            <Form.Text className="text-muted">Book's author</Form.Text>
          </Form.Group>

          <Form.Group controlId="genres">
            <Form.Label>Genres</Form.Label>
            <Form.Control
              as="select"
              multiple
              name="genres"
              value={selectedGenres}
              onChange={(e) =>
                this.handleChange("genres", e.target.selectedOptions, true)
              }
              style={this.props.style}
            >
              {genres}
            </Form.Control>
            <Form.Text className="text-muted">
              Book's genres. Use Control to select several lines
            </Form.Text>
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

  render() {
    if (!this.state.data) {
      return "";
    }

    const { data } = this.state;

    const rows = this.prepareRows(data);

    const delFlag = !!this.state.deleteObject;

    const name = this.state.deleteObject?.title || "";

    const handleClose = () => {
      this.setState({ deleteObject: null, editObject: null });
    };

    const handleDelete = () => {
      this.onDelete(this.state.deleteObject);
    };

    const handleCreate = () => {
      this.setState({
        deleteObject: null,
        editObject: { genres: [], author: {} },
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
      <div>
        <h1>Book</h1>
        {warningMessage}
        <Grid model={this.model} data={rows} />
        <Button variant="primary" onClick={handleCreate}>
          Create
        </Button>
        <Modal show={delFlag} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Delete</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            Delete Book <b>{name}</b> ?
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Cancel
            </Button>
            <Button variant="primary" onClick={handleDelete}>
              Delete
            </Button>
          </Modal.Footer>
        </Modal>
        {this.genEditDialog()}
      </div>
    );
  }
}

export default Books;
