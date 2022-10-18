package com.marina.hammersystems.presentation.mapper

import com.marina.hammersystems.domain.entity.CategoryEntity
import com.marina.hammersystems.presentation.entity.CategoryUI

fun List<CategoryEntity>.toUI(): List<CategoryUI> {
    return map {
        it.toUI()
    }
}

fun CategoryEntity.toUI(): CategoryUI {
    return CategoryUI(
        id = id,
        name = name
    )
}