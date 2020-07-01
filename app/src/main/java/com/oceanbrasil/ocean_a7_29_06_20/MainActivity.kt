package com.oceanbrasil.ocean_a7_29_06_20

import android.os.Bundle
import android.widget.Toast
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

        novaPosicao.latitude = 50.2
        val posicaoEditadaComSucesso = manager.editarPosicao(novaPosicao)

        if (!posicaoEditadaComSucesso) {
            Toast.makeText(this, "Posição não foi editada.", Toast.LENGTH_LONG).show()
        }

        val posicaoEditada = Posicao(2, 15.5, 20.6, currentDate)
        manager.editarPosicao(posicaoEditada)

        val posicaoSemId = Posicao(10.0, 10.0, currentDate)
        manager.editarPosicao(posicaoSemId)

//        manager.removerPosicao(posicaoEditada)

//        manager.limparPosicoes()

        val id = 10L
        val buscarPosicao = manager.obterPosicao(id)

        if (buscarPosicao != null) {
            Toast.makeText(this, "Posição de ID $id encontrada com sucesso.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Posição de ID $id não foi encontrada.", Toast.LENGTH_LONG).show()
        }

        val quantidadePosicoes = manager.obterQuantidadePosicoes()

        if (quantidadePosicoes >= 5) {
            manager.limparPosicoes()
        }

        val posicoes = manager.obterPosicoes()

        textView.text = ""

        posicoes.forEach {
            textView.append("ID: ${it.id}, Latitude: ${it.latitude}, Longitude: ${it.longitude}, Data/Hora: ${it.dataHora}\n")
        }
    }
}
