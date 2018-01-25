const topSchemes = (state = [], action) => {
  switch (action.type) {
    case 'SUCCESS_TOP_SCHEMES':
      return {
        ...state,
        ...action.payload
      }
    default:
      return state
  }
}

export default topSchemes