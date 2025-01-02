package com.reyaz.notesmongodb.domain.models

data class Notes (
    val title: String,
    val description: String,
    val label: String,
    val date: String = "10/12/2025"
)