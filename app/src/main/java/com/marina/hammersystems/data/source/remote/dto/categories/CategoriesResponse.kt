package com.marina.hammersystems.data.source.remote.dto.categories

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("data")
    val categories: List<Category>,
    @SerializedName("mensaje")
    val message: String,
    @SerializedName("ok")
    val isOk: Boolean
)