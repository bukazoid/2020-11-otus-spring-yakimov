
export default (state = {}, action) => {
  switch (action.type) {
    case "alert":
      return { alert: action.value };
    // case ACTION_2:
    //   return { value: action.value_2 };

    default:
      return state;
  }
};
