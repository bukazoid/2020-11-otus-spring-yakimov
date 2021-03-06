webpackHotUpdate("main",{

/***/ "./src/main/js/components/Books.js":
/*!*****************************************!*\
  !*** ./src/main/js/components/Books.js ***!
  \*****************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _common_Grid__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../common/Grid */ "./src/main/js/common/Grid.js");
/* harmony import */ var _common_GridProperty__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../common/GridProperty */ "./src/main/js/common/GridProperty.js");
/* harmony import */ var _common_Tools__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../common/Tools */ "./src/main/js/common/Tools.js");
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






var Books = /*#__PURE__*/function (_Component) {
  _inherits(Books, _Component);

  var _super = _createSuper(Books);

  function Books() {
    var _this;

    _classCallCheck(this, Books);

    _this = _super.call(this);

    _defineProperty(_assertThisInitialized(_this), "model", Books.getModel("Book", ["id", "title", "author", "genres", "actions"]));

    _defineProperty(_assertThisInitialized(_this), "loadData", function () {
      Object(_common_Tools__WEBPACK_IMPORTED_MODULE_3__["doFetch"])("/api/books", "GET").then(_common_Tools__WEBPACK_IMPORTED_MODULE_3__["handleApiError"]).then(function (response) {
        return response.json();
      }).then(function (json) {
        return _this.setState({
          data: json,
          refreshList: false
        });
      })["catch"](function (error) {
        return console.error(error);
      });
    });

    _this.state = {
      data: null,
      selected: null,
      showEdit: false,
      showDelete: false
    };
    return _this;
  } //model = Books.getModel("Book", ["id", "title", "author", "genres"]);


  _createClass(Books, [{
    key: "componentDidMount",
    value: function componentDidMount() {
      this.loadData();
    }
  }, {
    key: "componentDidUpdate",
    value: function componentDidUpdate(prevProps) {
      if (this.state.refreshList) {
        this.loadData();
      }
    }
  }, {
    key: "render",
    value: function render() {
      if (!this.state.data) {
        return "";
      }

      var data = this.state.data;
      var rows = data.map(function (book) {
        var rowBook = Object.assign({}, book);
        rowBook["author"] = book.author.name;
        var genres = book.genres;

        if (genres) {
          genres = genres.map(function (genre) {
            return genre.name;
          });
        }

        rowBook["genres"] = genres;
        rowBook["actions"] = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("span", null, "delete");
        return rowBook;
      });
      return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("div", null, /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement("h1", null, "Book: My React App!"), /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement(_common_Grid__WEBPACK_IMPORTED_MODULE_1__["default"], {
        model: this.model,
        data: rows
      }));
    }
  }]);

  return Books;
}(react__WEBPACK_IMPORTED_MODULE_0__["Component"]);

_defineProperty(Books, "getModel", function (msgPrefix, idList) {
  return idList.map(function (id) {
    return new _common_GridProperty__WEBPACK_IMPORTED_MODULE_2__["default"](id, id);
  });
});

/* harmony default export */ __webpack_exports__["default"] = (Books);

/***/ })

})
//# sourceMappingURL=main.a718d0760745a47d50a2.hot-update.js.map