package com.oceanbrasil.ocean_a7_29_06_20.db_exercicio

data class Repositorio(
    var id: Long?,
    val name: String,
    val html_url: String,
    val description: String,
    val owner_login: String,
    val owner_html_url: String,
    val criado: String
)