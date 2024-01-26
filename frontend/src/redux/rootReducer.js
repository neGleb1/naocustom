import { combineReducers } from "redux";
import product from "./product";
import productReducer from "./product";
import merchant from "./merchant";
import merchantReducer from "./merchant";

export default combineReducers({
  product: productReducer,
  merchant: merchantReducer
});