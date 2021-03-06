import React from "react";
import BootstrapTable from "react-bootstrap-table-next";
import PropTypes from "prop-types";
import GridProperty from "./GridProperty";
import "react-bootstrap-table-next/dist/react-bootstrap-table2.min.css";

/**
 * Here we use BootstrapTable (filters, pagination, sorting are already included, but installed as an independent librarys)
 * For more information see:
 * https://react-bootstrap-table.github.io/react-bootstrap-table2/
 */
export default class Grid extends React.Component {
  static propTypes = {
    model: PropTypes.arrayOf(PropTypes.instanceOf(GridProperty)).isRequired,
    data: PropTypes.arrayOf(PropTypes.any).isRequired,
  };
  /**
   * More sort options you can find on:
   * https://react-bootstrap-table.github.io/react-bootstrap-table2/docs/basic-sort.html
   */
  getDefaultSortOptions = () => {
    return [{ dataField: "id", order: "desc" }];
  };


  render() {
    const defaultSorted = this.getDefaultSortOptions();
    const { model, data } = this.props;

    const headers = model.map((prop) => {
      const label = prop.label;
      const header = { dataField: prop.id, text: label, sort: true };
      if (prop.id === "additional" || prop.id === "actions") {
        delete header.sort;
      }
      return header;
    });
    const emptyDataMessage = "no table data";
    return (
      <BootstrapTable
        keyField="id"
        data={data}
        columns={headers}
        striped
        hover
        condensed
        noDataIndication={emptyDataMessage}
        defaultSorted={defaultSorted}
      />
    );
  }
}
