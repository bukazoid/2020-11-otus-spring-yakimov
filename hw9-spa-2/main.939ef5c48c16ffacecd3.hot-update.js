webpackHotUpdate("main",{

/***/ "./src/main/js/common/Grid.js":
/*!************************************!*\
  !*** ./src/main/js/common/Grid.js ***!
  \************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "default", function() { return Grid; });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_bootstrap_table_next__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-bootstrap-table-next */ "./node_modules/react-bootstrap-table-next/lib/index.js");
/* harmony import */ var react_bootstrap_table_next__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(react_bootstrap_table_next__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var prop_types__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! prop-types */ "./node_modules/prop-types/index.js");
/* harmony import */ var prop_types__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(prop_types__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _GridProperty__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./GridProperty */ "./src/main/js/common/GridProperty.js");
/* harmony import */ var react_bootstrap_table_next_dist_react_bootstrap_table2_min_css__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! react-bootstrap-table-next/dist/react-bootstrap-table2.min.css */ "./node_modules/react-bootstrap-table-next/dist/react-bootstrap-table2.min.css");
/* harmony import */ var react_bootstrap_table_next_dist_react_bootstrap_table2_min_css__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(react_bootstrap_table_next_dist_react_bootstrap_table2_min_css__WEBPACK_IMPORTED_MODULE_4__);
function _typeof(obj) { "@babel/helpers - typeof"; if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") { _typeof = function _typeof(obj) { return typeof obj; }; } else { _typeof = function _typeof(obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; }; } return _typeof(obj); }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function"); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, writable: true, configurable: true } }); if (superClass) _setPrototypeOf(subClass, superClass); }

function _setPrototypeOf(o, p) { _setPrototypeOf = Object.setPrototypeOf || function _setPrototypeOf(o, p) { o.__proto__ = p; return o; }; return _setPrototypeOf(o, p); }

function _createSuper(Derived) { var hasNativeReflectConstruct = _isNativeReflectConstruct(); return function _createSuperInternal() { var Super = _getPrototypeOf(Derived), result; if (hasNativeReflectConstruct) { var NewTarget = _getPrototypeOf(this).constructor; result = Reflect.construct(Super, arguments, NewTarget); } else { result = Super.apply(this, arguments); } return _possibleConstructorReturn(this, result); }; }

function _possibleConstructorReturn(self, call) { if (call && (_typeof(call) === "object" || typeof call === "function")) { return call; } return _assertThisInitialized(self); }

function _assertThisInitialized(self) { if (self === void 0) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return self; }

function _isNativeReflectConstruct() { if (typeof Reflect === "undefined" || !Reflect.construct) return false; if (Reflect.construct.sham) return false; if (typeof Proxy === "function") return true; try { Boolean.prototype.valueOf.call(Reflect.construct(Boolean, [], function () {})); return true; } catch (e) { return false; } }

function _getPrototypeOf(o) { _getPrototypeOf = Object.setPrototypeOf ? Object.getPrototypeOf : function _getPrototypeOf(o) { return o.__proto__ || Object.getPrototypeOf(o); }; return _getPrototypeOf(o); }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }






/**
 * Here we use BootstrapTable (filters, pagination, sorting are already included, but installed as an independent librarys)
 * For more information see:
 * https://react-bootstrap-table.github.io/react-bootstrap-table2/
 */

var Grid = /*#__PURE__*/function (_React$Component) {
  _inherits(Grid, _React$Component);

  var _super = _createSuper(Grid);

  function Grid() {
    var _this;

    _classCallCheck(this, Grid);

    for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    _this = _super.call.apply(_super, [this].concat(args));

    _defineProperty(_assertThisInitialized(_this), "getDefaultSortOptions", function () {
      return [{
        dataField: "id",
        order: "desc"
      }];
    });

    return _this;
  }

  _createClass(Grid, [{
    key: "render",
    value: function render() {
      var defaultSorted = this.getDefaultSortOptions();
      var _this$props = this.props,
          model = _this$props.model,
          data = _this$props.data;
      var headers = model.map(function (prop) {
        var label = prop.label;
        var header = {
          dataField: prop.id,
          text: label,
          sort: true
        };

        if (prop.id === "additional" || prop.id === "actions") {
          delete header.sort;
        }

        return header;
      });
      var emptyDataMessage = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(FormattedMessage, {
        id: "common.no-table-data"
      }); //TODO emptyDataMessage should be in text format

      return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(react_bootstrap_table_next__WEBPACK_IMPORTED_MODULE_1___default.a, {
        keyField: "id",
        data: data,
        columns: headers,
        striped: true,
        hover: true,
        condensed: true,
        noDataIndication: emptyDataMessage,
        defaultSorted: defaultSorted
      });
    }
  }]);

  return Grid;
}(react__WEBPACK_IMPORTED_MODULE_0___default.a.Component);

_defineProperty(Grid, "propTypes", {
  model: prop_types__WEBPACK_IMPORTED_MODULE_2___default.a.arrayOf(prop_types__WEBPACK_IMPORTED_MODULE_2___default.a.instanceOf(_GridProperty__WEBPACK_IMPORTED_MODULE_3__["default"])).isRequired,
  data: prop_types__WEBPACK_IMPORTED_MODULE_2___default.a.arrayOf(prop_types__WEBPACK_IMPORTED_MODULE_2___default.a.any).isRequired
});



/***/ })

})
//# sourceMappingURL=main.939ef5c48c16ffacecd3.hot-update.js.map