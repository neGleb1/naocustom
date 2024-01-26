import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import axios from 'axios'
import UserService from '../config/UserService'

const initialState = {
  content: [],
  isLoading: false,
  error: null,
}

export const productSlice = createSlice({
  name: 'product',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(getProduct.pending, (state) => {
      state.isLoading = true
    })
    builder.addCase(getProduct.fulfilled, (state, action) => {
      state.isLoading = false
      state.content = action.payload
    })
    builder.addCase(getProduct.rejected, (state, action) => {
      state.isLoading = false
      state.error = action.error.message
    })
    builder.addCase(getProducts.pending, (state) => {
      state.isLoading = true
    })
    builder.addCase(getProducts.fulfilled, (state, action) => {
      state.isLoading = false
      state.content = action.payload
    })
    builder.addCase(getProducts.rejected, (state, action) => {
      state.isLoading = false
      state.error = action.error.message
    })
    builder.addCase(getProductsByQuery.pending, (state) => {
      state.isLoading = true
    })
    builder.addCase(getProductsByQuery.fulfilled, (state, action) => {
      state.isLoading = false
      state.content = action.payload
    })
    builder.addCase(getProductsByQuery.rejected, (state, action) => {
      state.isLoading = false
      state.error = action.error.message
    })
    builder.addCase(getProductsByCategory.pending, (state) => {
      state.isLoading = true
    })
    builder.addCase(getProductsByCategory.fulfilled, (state, action) => {
      state.isLoading = false
      state.content = action.payload
    })
    builder.addCase(getProductsByCategory.rejected, (state, action) => {
      state.isLoading = false
      state.error = action.error.message
    })
    builder.addCase(getMerchantProducts.pending, (state) => {
      state.isLoading = true
    })
    builder.addCase(getMerchantProducts.fulfilled, (state, action) => {
      state.isLoading = false
      state.content = action.payload
    })
    builder.addCase(getMerchantProducts.rejected, (state, action) => {
      state.isLoading = false
      state.error = action.error.message
    })
    builder.addCase(addProduct.pending, (state) => {
      state.isLoading = true
    })
    builder.addCase(addProduct.fulfilled, (state, action) => {
      state.isLoading = false
      state.content = action.payload
    })
    builder.addCase(addProduct.rejected, (state, action) => {
      state.isLoading = false
      state.error = action.error.message
    })
    builder.addCase(deleteProduct.pending, (state) => {
      state.isLoading = true
    })
    builder.addCase(deleteProduct.fulfilled, (state, action) => {
      state.isLoading = false
      state.content = action.payload
    })
    builder.addCase(deleteProduct.rejected, (state, action) => {
      state.isLoading = false
      state.error = action.error.message
    })
    builder.addCase(updateProduct.pending, (state) => {
      state.isLoading = true
    })
    builder.addCase(updateProduct.fulfilled, (state, action) => {
      state.isLoading = false
      state.content = action.payload
    })
    builder.addCase(updateProduct.rejected, (state, action) => {
      state.isLoading = false
      state.error = action.error.message
    })
  },
})

export default productSlice.reducer;

export const getProducts = createAsyncThunk('product/getProducts', async () => {
  const res = await axios.get('https://localhost/api/products');
  const data = await res.data;
  return data;
});

export const getProductsByQuery = createAsyncThunk('product/getProductsByQuery', async (query) => {
  const res = await axios.get(`https://localhost/api/products/search?q=${query}`);
  const data = await res.data;
  return data;
});

export const getProduct = createAsyncThunk('product/getProduct', async (productId) => {
  const res = await axios.get(`https://localhost/api/products/${productId}`);
  const data = await res.data;
  return data;
});

export const getProductsByCategory = createAsyncThunk('product/getProductsByCategory', async ([sex, category]) => {
  const res = await axios.get(`https://localhost/api/products/product?sex=${sex}&category=${category}`);
  const data = await res.data;
  return data;
});

export const getMerchantProducts = createAsyncThunk('product/getMerchantProducts', async (merchantId) => {
  const res = await axios.get(`https://localhost/api/merchant/${merchantId}/products`,{
    headers: {
      'Authorization': 'Bearer ' + UserService.getToken()
    }
  });
  const data = await res.data;
  return data;
});

export const addProduct = createAsyncThunk('product/addProduct', async ([
  merchantId,
  formData
]) => {
  const res = await axios.post(`https://localhost/api/merchant/${merchantId}/products`,
  formData, {
    headers: {
      'Authorization': 'Bearer ' + UserService.getToken(),
    }
  });
  const data = await res.data;
  return data;
});

export const deleteProduct = createAsyncThunk('product/deleteProduct', async ([merchantId, productId]) => {
  const res = await axios.delete(`https://localhost/api/merchant/${merchantId}/products/${productId}`, {
    headers: {
      'Authorization': 'Bearer ' + UserService.getToken()
    }
  });
  const data = await res.data;
  return data;
});

export const updateProduct = createAsyncThunk('product/updateProduct', async ([merchantId, productId, formData]) => {
  const res = await axios.put(`https://localhost/api/merchant/${merchantId}/products/${productId}`, formData, {
    headers: {
      'Authorization': 'Bearer ' + UserService.getToken(),
      'Content-Type': 'multipart/form-data'
    }
  });
  const data = await res.data;
  return data;
});