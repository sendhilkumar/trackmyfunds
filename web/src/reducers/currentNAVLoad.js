const currentNAVLoad = (state = null, action) => {
  switch (action.type) {
    case 'SUCCESS_LOAD_CURRENT_NAV':
      return { ...action.payload }
    default:
      return state
  }
}

export default currentNAVLoad