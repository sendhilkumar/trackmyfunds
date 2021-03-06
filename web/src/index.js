import React from 'react'
import { render } from 'react-dom'
import { Provider } from 'react-redux'
import { createStore, applyMiddleware, combineReducers } from 'redux';
import { apiMiddleware } from 'redux-api-middleware';
import logger from 'redux-logger'
import { Router, Route, IndexRoute, browserHistory } from 'react-router'
import { syncHistoryWithStore, routerReducer } from 'react-router-redux'

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './index.css';

import PortfolioCurrentValue from './components/PortfolioCurrentValue';
import PortfolioHistory from './components/PortfolioHistory';
import Portfolios from './components/Portfolios';
import NAVAndTxPlot from './components/plot/NAVAndTxPlot';
import Admin from './components/Admin';
import App from './components/App';
import StatementUpload from './components/StatementUpload';
import TopSchemes from './components/TopSchemes';

import hist from './reducers/hist'
import currentPortfolioValue from './reducers/currentPortfolioValue'
import currentNAVLoad from './reducers/currentNAVLoad'
import portfolios from './reducers/portfolios'
import portfolioHistory from './reducers/portfolioHistory'
import topSchemes from './reducers/topSchemes'

const reducers = combineReducers({
  hist,
  currentPortfolioValue,
  currentNAVLoad,
  portfolios,
  portfolioHistory,
  topSchemes,
  routing: routerReducer
})

const createStoreWithMiddleware = applyMiddleware(apiMiddleware, logger)(createStore);

let store = createStoreWithMiddleware(reducers, {});

const history = syncHistoryWithStore(browserHistory, store);

render(
  <Provider store={store}>
    <Router history={history}>

      <Route path="/web" component={App}>
        <IndexRoute component={Portfolios} />
        <Route path="upload" component={StatementUpload} />

        <Route path="current/:portfolioId" component={PortfolioCurrentValue} />
        <Route path="history/:fundId" component={NAVAndTxPlot} />
        <Route path="admin" component={Admin} />
        <Route path="history" component={PortfolioHistory} />
        <Route path="top" component={TopSchemes} />

      </Route>
    </Router>
  </Provider>,
  document.getElementById('root')
)

