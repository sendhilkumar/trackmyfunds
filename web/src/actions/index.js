import { CALL_API } from 'redux-api-middleware';

export const getHistory = (fundId) =>{
    return {
      [CALL_API]: {
        endpoint: `/api/schemes/history/${fundId}`,
        method: 'GET',
        types: [
                  {
                    type: 'REQUEST_HISTORY',
                    meta: { schemeCode: fundId }
                  },
                  {
                    type: 'SUCCESS_HISTORY',
                    meta: { schemeCode: fundId }
                  },
                  {
                    type: 'FAILURE_HISTORY',
                    meta: { schemeCode: fundId }
                  }
               ]
      }
    }
}

export const getPortfolioCurrentValue =(portfolioId) => {
  return {
    [CALL_API]: {
      endpoint: `/api/portfolio/today/${portfolioId}`,
      method: 'GET',
      types: ['REQUEST', 'SUCCESS', 'FAILURE']
    }
  }
}

export const loadCurrentNAV =() => {
  return {
    [CALL_API]: {
      endpoint: `/api/schemes/loadCurrentNAV`,
      method: 'POST',
      types: ['REQUEST_LOAD_CURRENT_NAV', 'SUCCESS_LOAD_CURRENT_NAV', 'FAILURE_LOAD_CURRENT_NAV']
    }
  }
}

