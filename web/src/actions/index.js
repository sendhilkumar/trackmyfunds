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
      endpoint: `/api/portfolio/${portfolioId}/today`,
      method: 'GET',
      types: [
        {
          type: 'REQUEST_PORTFOLIO_VALUE',
          meta: { portfolioId: portfolioId }
        },
        {
          type: 'SUCCESS_PORTFOLIO_VALUE',
          meta: { portfolioId: portfolioId }
        },
        {
          type: 'FAILURE_PORTFOLIO_VALUE',
          meta: { portfolioId: portfolioId }
        }
     ]
    }
  }
}

export const getPortfolioHistory =(portfolioId) => {
  return {
    [CALL_API]: {
      endpoint: `/api/portfolio/${portfolioId}/history`,
      method: 'GET',
      types: [
        {
          type: 'REQUEST_PORTFOLIO_HISTORY',
          meta: { portfolioId: portfolioId }
        },
        {
          type: 'SUCCESS_PORTFOLIO_HISTORY',
          meta: { portfolioId: portfolioId }
        },
        {
          type: 'FAILURE_PORTFOLIO_HISTORY',
          meta: { portfolioId: portfolioId }
        }
     ]
    }
  }
}

export const getPortfolios =() => {
  return {
    [CALL_API]: {
      endpoint: `/api/portfolio/subportfolios`,
      method: 'GET',
      types: ['REQUEST_PORTFOLIOS', 'SUCCESS_PORTFOLIOS', 'FAILURE_PORTFOLIOS']
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

export const loadNAV =(from, to) => {
  return {
    [CALL_API]: {
      endpoint: `/api/admin/loadNAV?from=${from}&to=${to}`,
      method: 'POST',
      types: ['REQUEST_LOAD_NAV', 'SUCCESS_LOAD_NAV', 'FAILURE_LOAD_NAV']
    }
  }
}

