package com.oceanbrasil.ocean_a7_29_06_20.db

data class Posicao(
    var id: Long?,
    var latitude: Double,
    var longitude: Double,
    val dataHora: String
) {
    constructor(latitude: Double, longitude: Double, dataHora: String) : this(null, latitude, longitude, dataHora)
}
