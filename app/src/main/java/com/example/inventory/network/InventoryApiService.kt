package com.example.inventory.network

import com.example.inventory.data.Item
import retrofit2.http.*

interface InventoryApiService {
    @GET("get_all.php")
    suspend fun getAllItems(): List<Item>

    @FormUrlEncoded
    @POST("insert.php")
    suspend fun insertItem(
        @Field("name") name: String,
        @Field("price") price: Double,
        @Field("quantity") quantity: Int
    )

    @FormUrlEncoded
    @POST("update.php")
    suspend fun updateItem(
        @Field("id") id: Int,
        @Field("name") name: String,
        @Field("price") price: Double,
        @Field("quantity") quantity: Int
    )

    @FormUrlEncoded
    @POST("delete.php")
    suspend fun deleteItem(@Field("id") id: Int)
}