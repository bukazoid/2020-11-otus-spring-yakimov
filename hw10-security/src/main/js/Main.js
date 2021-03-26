import React from 'react';
import { Switch, Route, Router, BrowserRouter } from 'react-router-dom';

import Root from './components/Root';
import Books from './components/Books';
import Authors from './components/Authors';
import Genres from './components/Genres';
import Login from './components/Login';


import NotFound from './components/NotFound';

const  Main = () => {
	return (
		<center>
			<a href="/">root</a> <a href="/page/books">books</a> <a href="/page/genres">genres</a>  <a href="/page/authors">authors</a>   <a href="/logout">logout</a>   
			<hr />
			<Switch>
				<Route exact path='/' component={Root} />
				<Route path='/page/books' component={Books} />
				<Route path='/page/authors' component={Authors} />
				<Route path='/page/genres' component={Genres} />
				<Route path='/page/login' component={Login} />
				
				<Route component={NotFound} />
			</Switch>			
		</center>
	);
}


export default Main;