import { createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";
import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    user:{ name:'User 1'}
}

export const userSlice = createSlice({
    name: 'user',
    initialState: initialState,
    reducers: {
        updateUser(state, action) {
            
            console.log('Here')
             state.user = action.payload;
        },
    }
})

export const { updateUser } = userSlice.actions
export default userSlice.reducer
