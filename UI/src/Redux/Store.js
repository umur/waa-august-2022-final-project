import { configureStore } from '@reduxjs/toolkit'
import userReducer from './../Redux/UserSlice'


 

export const store = configureStore({
  reducer: {
     userReducer: userReducer,
  },
})

