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
import NAVAndTxPlot from './components/plot/NAVAndTxPlot';
import Admin from './components/Admin';
import App from './components/App';
import StatementUpload from './components/StatementUpload';

import hist from './reducers/hist'
import currentPortfolioValue from './reducers/currentPortfolioValue'
import currentNAVLoad from './reducers/currentNAVLoad'

const reducers = combineReducers({
  hist,
  currentPortfolioValue,
  currentNAVLoad,
  routing: routerReducer
})

const createStoreWithMiddleware = applyMiddleware(apiMiddleware, logger)(createStore);

let store = createStoreWithMiddleware(reducers, {});

const history = syncHistoryWithStore(browserHistory, store);

render(
  <Provider store={store}>
    <Router history={history}>

      <Route path="/web" component={App}>
        <IndexRoute component={PortfolioCurrentValue} />
        <Route path="upload" component={StatementUpload} />

        <Route path="current/:portfolioId" component={PortfolioCurrentValue} />
        <Route path="history/:fundId" component={NAVAndTxPlot} />
        <Route path="admin" component={Admin} />

      </Route>
    </Router>
  </Provider>,
  document.getElementById('root')
)

