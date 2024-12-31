package com.example.getapi.adapter

import com.example.getapi.ResponseModel

interface RecyclerInterface {
    fun delete(position: Int)
    fun itemClick(position: Int , model: ResponseModel.Data)
}
