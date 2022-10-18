package com.marina.hammersystems.data.source.remote.dto.categories

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nombre")
    val name: String
)