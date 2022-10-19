package com.marina.hammersystems.data.mapper

import com.marina.hammersystems.data.source.local.entity.CategoryDB
import com.marina.hammersystems.data.source.remote.dto.categories.Category
import com.marina.hammersystems.domain.entity.CategoryEntity

fun List<Category>.toDomain(): List<CategoryEntity> {
    return map {
        it.toDomain()
    }
}

fun Category.toDomain(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name
    )
}

fun List<Category>.toDB(): List<CategoryDB> {
    return map {
        it.toDB()
    }
}

fun Category.toDB(): CategoryDB {
    return CategoryDB(
        id = id,
        name = name
    )
}

fun List<CategoryDB>.toEntity(): List<CategoryEntity> {
    return map {
        it.toDomain()
    }
}

fun CategoryDB.toDomain(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name
    )
}