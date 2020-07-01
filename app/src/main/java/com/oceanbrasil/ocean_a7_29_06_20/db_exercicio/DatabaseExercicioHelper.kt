package com.oceanbrasil.ocean_a7_29_06_20.db_exercicio

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseExercicioHelper(context: Context) : SQLiteOpenHelper(context, "ocean_db_exercicio", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.execSQL(
                "CREATE TABLE repositorios (" +
                        "id integer PRIMARY KEY," +
                        "name varchar(255)," +
                        "html_url varchar(255)," +
                        "description varchar(255)," +
                        "language varchar(255)," +
                        "owner_login varchar(255)," +
                        "owner_html_url varchar(255)," +
                        "criado varchar(255)" +
                    ");"
            )
        } catch (e: Exception) {
            Log.e("BANCO_DADOS", "Erro ao criar banco de dados.", e)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}