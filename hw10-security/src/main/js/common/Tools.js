const APP_BASE_URL = "/";
const JSON_TYPE = "application/json";

export const handleApiError = (response) => {
  // console.log("response status: " + response.status);
  console.log("response location: " + response.headers.get("Location"));

  console.log("response text: " + response.data);  

  if (response.ok) return response;

  switch (response.status) {
    case 401:
      window.location.href = APP_BASE_URL;
      throw Promise.resolve("Error.unauthorized-user");
    case 403:
      console.log("Client Error: " + response.statusText);
      window.location.href = APP_BASE_URL;
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
export const doFetch = (url, method, data, contentType) => {
  if (!contentType) {
    contentType = JSON_TYPE;
  }
  const params = {
    method: method,
    body: data,
    headers: {
      "Content-Type": contentType,
    },
//    redirect: "manual",
  };
  if (data) params.body = data;

  return fetch(url, params);
};
