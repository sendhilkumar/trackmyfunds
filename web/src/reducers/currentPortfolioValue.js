const currentPortfolioValue = (state = [], action) => {
  switch (action.type) {
    case 'SUCCESS':
      return {
        ...state,
        ...action.payload
      }
    default:
      return state
  }
}

export default currentPortfolioValue