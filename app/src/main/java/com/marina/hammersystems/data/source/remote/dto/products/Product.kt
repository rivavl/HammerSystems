package com.marina.hammersystems.data.source.remote.dto.products

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("descripcion")
    val description: String?,
    @SerializedName("linkImagen")
    val imageUrl: String?,
    @SerializedName("nombre")
    val name: String,
    @SerializedName("precio")
    val price: String?
)