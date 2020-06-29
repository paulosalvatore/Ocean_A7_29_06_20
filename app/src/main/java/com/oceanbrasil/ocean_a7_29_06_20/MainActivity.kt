package com.oceanbrasil.ocean_a7_29_06_20

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oceanbrasil.ocean_a7_29_06_20.db.DatabaseHelper
import com.oceanbrasil.ocean_a7_29_06_20.db.DatabaseManager
import com.oceanbrasil.ocean_a7_29_06_20.db.Posicao
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helper = DatabaseHelper(this)
        val manager = DatabaseManager(helper)

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val currentDate = sdf.format(Date())

        val novaPosicao = Posicao(15.5, 10.2, currentDate)
        manager.criarPosicao(novaPosicao)

        val posicoes = manager.obterPosicoes()

        textView.text = ""

        posicoes.forEach {
            textView.append("ID: ${it.id}, Latitude: ${it.latitude}, Longitude: ${it.longitude}, Data/Hora: ${it.dataHora}\n")
        }
    }
}
