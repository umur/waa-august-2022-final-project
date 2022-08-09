import { createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";
import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    user: { name: 'User 1' },
    appliedJobs:[]
}

export const applyJob = createAsyncThunk(
    'students/appliedJob',
    async (params) => {
        
        const response = await axios.post(`students/${params.userId}/job-advertisements/${params.advId}`);
        return response.data;
    }
)

export const userSlice = createSlice({
    name: 'user',
    initialState: initialState,
    reducers: {
        updateUser(state, { payload }) {

            console.log('Here')
            state.user = payload;
            console.log(state.user)
        },
    },
    extraReducers: (builder) => {
        builder.addCase((applyJob).fulfilled, (state, action) => {
            state.appliedJobs = action.payload
        });
    },
})

export const { updateUser } = userSlice.actions
export default userSlice.reducer
