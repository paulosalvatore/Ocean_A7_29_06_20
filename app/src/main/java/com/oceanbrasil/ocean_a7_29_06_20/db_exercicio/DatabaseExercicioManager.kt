package com.oceanbrasil.ocean_a7_29_06_20.db_exercicio

import android.content.ContentValues

class DatabaseExercicioManager(val helper: DatabaseExercicioHelper) {
    fun criarRepositorio(repositorio: Repositorio) {
        val db = helper.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("name", repositorio.name)
        contentValues.put("html_url", repositorio.html_url)
        contentValues.put("description", repositorio.description)
        contentValues.put("owner_login", repositorio.owner_login)
        contentValues.put("owner_html_url", repositorio.owner_html_url)
        contentValues.put("criado", repositorio.criado)

        val id = db.insert("repositorios", null, contentValues)

        db.close()

        repositorio.id = id
    }

    fun obterRepositorios(): List<Repositorio> {
        val repositorios = mutableListOf<Repositorio>()

        val db = helper.readableDatabase

        val sqlQuery = "SELECT * FROM repositorios"

        val cursor = db.rawQuery(sqlQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndex("id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val htmlUrl = cursor.getString(cursor.getColumnIndex("html_url"))
                val description = cursor.getString(cursor.getColumnIndex("description"))
                val ownerLogin = cursor.getString(cursor.getColumnIndex("owner_login"))
                val ownerHtmlUrl = cursor.getString(cursor.getColumnIndex("owner_html_url"))
                val criado = cursor.getString(cursor.getColumnIndex("criado"))

                val repositorio = Repositorio(id, name, htmlUrl, description, ownerLogin, ownerHtmlUrl, criado)
                repositorios.add(repositorio)
            } while (cursor.moveToNext())
        }

        db.close()

        return repositorios
    }
}
