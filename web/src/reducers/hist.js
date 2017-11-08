const hist = (state = [], action) => {
  switch (action.type) {
    case 'SUCCESS_HISTORY':
      return [
        ...action.payload
      ]
    default:
      return state
  }
}

export default hist