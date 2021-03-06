import React, { Component } from "react";
import Grid from "../common/Grid";
import GridProperty from "../common/GridProperty";
import { handleApiError, doFetch } from "../common/Tools";

class Books extends Component {
  constructor() {
    super();
    this.state = {
      data: null,
      selected: null,
      showEdit: false,
      showDelete: false,
    };
  }

  //model = Books.getModel("Book", ["id", "title", "author", "genres"]);
  model = Books.getModel("Book", [
    "id",
    "title",
    "author",
    "genres",
    "actions",
  ]);

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
      .catch((error) => console.error(error));
    this.cancelDelete();
  };

  loadData = () => {
    doFetch("/api/books", "GET")
      .then(handleApiError)
      .then((response) => response.json())
      .then((json) => this.setState({ data: json, refreshList: false }))
      .catch((error) => console.error(error));
  };

  static getModel = (msgPrefix, idList) => {
    return idList.map((id) => new GridProperty(id, id));
  };

  render() {
    if (!this.state.data) {
      return "";
    }

    const { data } = this.state;

    const rows = data.map((book) => {
      const rowBook = Object.assign({}, book);
      rowBook["author"] = book.author.name;
      let genres = book.genres;
      if (genres) {
        genres = genres.map((genre) => {
          return genre.name + " ";
        });
      }
      rowBook["genres"] = genres;

      const deleteLink = (
        <a href="#" title="delete" onClick={this.confirmDelete(book)}>
          delete
        </a>
      );

      rowBook["actions"] = <span>delete</span>;

      return rowBook;
    });

    return (
      <div>
        <h1>Book: My React App!</h1>
        <Grid model={this.model} data={rows} />
      </div>
    );
  }
}

export default Books;
