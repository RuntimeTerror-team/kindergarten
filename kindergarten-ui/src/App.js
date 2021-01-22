import React from 'react';
import { Route, Switch } from 'react-router-dom';
import AdminPageContainer from './components/AdminPage/AdminPageContainer';
import HomepageContainer from "./components/Homepage/HomepageContainer";

function App(props) {
  return (
  <div>
    {/* <Navigation /> */}
    <Switch>
      <Route exact path='/' component={HomepageContainer} />
      <Route exact path='/admin' component={AdminPageContainer} />
      {/* <Route path='/products/:id' component={ProductDetails} />
      <Route path='/products' component={ProductListContainer} />
      <Route path='/admin/products/new' component={ProductAdministrationFormContainer} />
      <Route path='/admin/products/:id' component={ProductAdministrationFormContainer} />
      <Route path='/admin/products' component={AdminTableContainer} />
      <Route path='/cart-products' component={CartDetailsContainer} />
      <Route path='*' component={NoMatch} />
      <Route component={NoMatch} /> */}
    </Switch>
    {props.children}
  </div>
  )
}

export default App;
