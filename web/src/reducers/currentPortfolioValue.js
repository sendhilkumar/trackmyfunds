const currentPortfolioValue = (state = [], action) => {
  switch (action.type) {
    case 'SUCCESS_PORTFOLIO_VALUE':
      {
        const portfolioId = action.meta.portfolioId;
        const newState = { ...state };
        newState[portfolioId] = action.payload
        return newState;
      }
    default:
      return state
  }
}

export default currentPortfolioValue