const portfolios = (state = [], action) => {
  switch (action.type) {
    case 'SUCCESS_PORTFOLIOS':
      return {
        ...state,
        ...action.payload
      }
    default:
      return state
  }
}

export default portfolios