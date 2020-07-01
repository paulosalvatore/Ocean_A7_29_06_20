package com.oceanbrasil.ocean_a7_29_06_20.db

import android.content.ContentValues

class DatabaseManager(val helper: DatabaseHelper) {
    fun criarPosicao(posicao: Posicao) {
        val db = helper.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("latitude", posicao.latitude)
        contentValues.put("longitude", posicao.longitude)
        contentValues.put("data_hora", posicao.dataHora)

        val id = db.insert("posicoes", null, contentValues)

        db.close()

        posicao.id = id
    }

    fun obterPosicoes(): List<Posicao> {
        val posicoes = mutableListOf<Posicao>()

        val db = helper.readableDatabase

        val sqlQuery = "SELECT * FROM posicoes"

        val cursor = db.rawQuery(sqlQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndex("id"))
                val latitude = cursor.getDouble(cursor.getColumnIndex("latitude"))
                val longitude = cursor.getDouble(cursor.getColumnIndex("longitude"))
                val dataHora = cursor.getString(cursor.getColumnIndex("data_hora"))

                val posicao = Posicao(id, latitude, longitude, dataHora)
                posicoes.add(posicao)
            } while (cursor.moveToNext())
        }

        db.close()

        return posicoes
    }
}
