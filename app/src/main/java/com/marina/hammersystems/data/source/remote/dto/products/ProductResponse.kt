package com.marina.hammersystems.data.source.remote.dto.products

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("mensaje")
    val message: String,
    @SerializedName("ok")
    val isOk: Boolean,
    @SerializedName("productos")
    val products: List<Product>
)