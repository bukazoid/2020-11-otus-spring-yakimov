const SET_ALERT = "SET_ALERT";

const defaultState = {
  alertMessage: null,
  justTest: "justTest"
};

export default function reposReducer(state = defaultState, action) {
  console.log("state: " + JSON.stringify(state));
  switch (action.type) {
    case SET_ALERT:
      return {
        ...state,
        alertMessage: action.payload,
      };
    default:
      return state;
  }
}

export const setAlert = (alertText) => ({
  type: SET_ALERT,
  payload: alertText,
});
