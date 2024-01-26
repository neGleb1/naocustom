import axios from 'axios';
import { StrictMode } from 'react';
import { createRoot } from "react-dom/client";
import { applyMiddleware, createStore } from "redux";
import { Provider } from 'react-redux'
import axiosMiddleware from "redux-axios-middleware";
import { thunk } from "redux-thunk";
import App from "./components/App";
import rootReducer from "./redux/rootReducer";
import UserService from "./config/UserService";
import './styles/index.css';

// https://naocustom.com
const _axios = axios.create({ baseURL: 'https://localhost' });
_axios.interceptors.request.use((config) => {
  if (UserService.isLoggedIn()) {
    const configHeaders = () => {
      config.headers.Authorization = `Bearer ${UserService.getToken()}`;
      return Promise.resolve(config);
    };
    return UserService.updateToken(configHeaders);
  }
});

const _middleware = applyMiddleware(thunk, axiosMiddleware(_axios));
const store = createStore(rootReducer, _middleware);

const root = () => createRoot(document.getElementById('root'))
.render(
  <StrictMode>
    <Provider store={store}>
      <App/>
    </Provider>
  </StrictMode>
);

UserService.initKeycloak(root);

// ReactDOM.createRoot(document.getElementById('root')).render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>,
// )
