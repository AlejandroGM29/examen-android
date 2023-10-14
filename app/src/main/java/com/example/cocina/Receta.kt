package com.example.cocina

data class Receta(
    val nombre: String,
    val ingredientes: String,
    val rating: Float, // Campo para almacenar la calificación de la receta
    var esFavorita: Boolean = false // Campo para marcar si la receta es favorita
)
