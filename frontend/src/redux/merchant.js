import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import axios from 'axios'
import UserService from '../config/UserService'

const initialState = {
  content: [],
  isLoading: false,
  error: null,
}

export const merchantSlice = createSlice({
    name: 'merchant',
    initialState,
    reducers: {},
    extraReducers: (builder) => {
      builder.addCase(getMerchant.pending, (state) => {
        state.isLoading = true
      })
      builder.addCase(getMerchant.fulfilled, (state, action) => {
        state.isLoading = false
        state.content = action.payload
      })
      builder.addCase(getMerchant.rejected, (state, action) => {
        state.isLoading = false
        state.error = action.error.message
      })
    },
});

export default merchantSlice.reducer;

export const getMerchant = createAsyncThunk('merchant/getMerchant', async (email) => {
    const res = await axios.get(`https://localhost/api/merchant/${email}`,{
      headers: {
        'Authorization': 'Bearer ' + UserService.getToken()
      }
    });
    const data = await res.data;
    return data;
});