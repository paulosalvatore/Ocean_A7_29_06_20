package com.oceanbrasil.ocean_a7_29_06_20.db

import android.content.ContentValues
import android.database.DatabaseUtils

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

    fun obterPosicao(id: Long): Posicao? {
        val db = helper.readableDatabase

        val sqlQuery = "SELECT * FROM posicoes WHERE id = $id;"
        val cursor = db.rawQuery(sqlQuery, null)

        if (cursor.moveToFirst()) {
            val latitude = cursor.getDouble(cursor.getColumnIndex("latitude"))
            val longitude = cursor.getDouble(cursor.getColumnIndex("longitude"))
            val dataHora = cursor.getString(cursor.getColumnIndex("data_hora"))

            return Posicao(id, latitude, longitude, dataHora)
        }

        db.close()

        return null
    }

    fun editarPosicao(posicao: Posicao): Boolean {
        if (posicao.id == null) {
            return false
        }

        val db = helper.writableDatabase

        val contentValues = ContentValues()
        contentValues.put("latitude", posicao.latitude)
        contentValues.put("longitude", posicao.longitude)
        contentValues.put("data_hora", posicao.dataHora)

        val retornoUpdate = db.update("posicoes", contentValues, "id = ?", arrayOf(posicao.id.toString()))

        db.close()

        return retornoUpdate == 1
    }

    fun removerPosicao(posicao: Posicao): Boolean {
        if (posicao.id == null) {
            return false
        }

        val db = helper.writableDatabase

        val retornoDelete = db.delete("posicoes", "id = ?", arrayOf(posicao.id.toString()))

        db.close()

        return retornoDelete == 1
    }

    fun limparPosicoes(): Boolean {
        val db = helper.writableDatabase

//        val sqlQuery = "DELETE FROM posicoes"
//        db.execSQL(sqlQuery)

        val retornoDelete = db.delete("posicoes", "", emptyArray())

        db.close()

        return retornoDelete > 0
    }

    fun obterQuantidadePosicoes(): Long {
        val db = helper.readableDatabase

        val count = DatabaseUtils.queryNumEntries(db, "posicoes")

        db.close()

        return count
    }
}
