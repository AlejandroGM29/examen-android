package com.example.cocina

import AgregarRecetaFragment
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.cocina.R

class MainActivity : AppCompatActivity(), ListaRecetasFragment.OnRecetaSelectedListener {
    private val listaRecetas = mutableListOf<Receta>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ListaRecetasFragment())
            .commit()
    }
    fun agregarReceta(receta: Receta) {
        listaRecetas.add(receta)
    }

    fun obtenerRecetas(): List<Receta> {
        return listaRecetas
    }
    override fun onRecetaSelected(receta: Receta) {
        // Implementa la lógica para manejar la selección de recetas aquí
    }

    fun mostrarFragmento(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_ver_recetas -> {
                mostrarFragmento(ListaRecetasFragment())
                true
            }
            R.id.action_agregar_receta -> {
                mostrarFragmento(AgregarRecetaFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
