const hist = (state = [], action) => {
  switch (action.type) {
    case 'SUCCESS_HISTORY':
      {
        const schemeCode = action.meta.schemeCode;
        const newState = { ...state };
        newState[schemeCode] = action.payload
        return newState;
      }

    default:
      return state
  }
}

export default hist