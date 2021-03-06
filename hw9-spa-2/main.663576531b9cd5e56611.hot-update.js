webpackHotUpdate("main",{

/***/ "./src/main/js/common/Tools.js":
/*!*************************************!*\
  !*** ./src/main/js/common/Tools.js ***!
  \*************************************/
/*! exports provided: handleApiError, doFetch */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "handleApiError", function() { return handleApiError; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "doFetch", function() { return doFetch; });
var APP_BASE_URL = "/";
var JSON_TYPE = "application/json";
var handleApiError = function handleApiError(response) {
  if (response.ok) return response;

  switch (response.status) {
    case 401:
      removeToken();
      window.location.href = APP_BASE_URL;
      throw Promise.resolve("Error.unauthorized-user");

    case 403:
      console.log("Client Error: " + response.statusText);
      throw Promise.resolve("Error.invalid-pass-or-login");

    case 404:
      console.log("Client Error: " + response.statusText);
      throw Promise.resolve("Error.resource-not-found");

    case 500:
      console.log("Server Error: " + response.statusText);
      throw Promise.resolve("Error.internal-server-error");

    case 503:
    case 504:
      console.log("Server Error: " + response.statusText);
      throw Promise.resolve("Error.server-not-available");

    default:
      throw response.text();
  }
};
/**
 * supports upload too
 */

var doFetch = function doFetch(url, method, data) {
  var params = {
    method: method,
    body: data,
    headers: {
      "Content-Type": JSON_TYPE
    }
  };
  if (data) params.body = data;
  return fetch(APP_BASE_URL + url, params);
};

/***/ })

})
//# sourceMappingURL=main.663576531b9cd5e56611.hot-update.js.map