package com.example.cocina

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaRecetasFragment : Fragment(), RecyclerItemTouchHelperListener {

    interface OnRecetaSelectedListener {
        fun onRecetaSelected(receta: Receta)
    }

    private lateinit var recetaAdapter: RecetaAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_recetas, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Obtener las recetas de la actividad principal
        // Obtener las recetas de la actividad principal y convertirlas a una lista mutable
        val recetas = (requireActivity() as MainActivity).obtenerRecetas().toMutableList()

// Configurar el adaptador del RecyclerView con las recetas obtenidas
        recetaAdapter = RecetaAdapter(recetas)
        recyclerView.adapter = recetaAdapter

        // Configurar el ItemTouchHelper para manejar el deslizamiento
        val itemTouchHelperCallback = RecyclerItemTouchHelper(requireContext(), this)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // Manejar la selección de recetas
        recetaAdapter.setOnItemClickListener(object : RecetaAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                // Notificar al listener cuando se selecciona una receta
                (requireActivity() as OnRecetaSelectedListener).onRecetaSelected(recetas[position])
            }
        })

        return view
    }

    override fun showDeleteConfirmationDialog(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Eliminar receta")
        builder.setMessage("¿Estás seguro de que deseas eliminar esta receta?")
        builder.setPositiveButton("Sí") { _, _ ->
            // Eliminar la receta de la lista y notificar al adaptador
            (recyclerView.adapter as RecetaAdapter).eliminarReceta(position)
            recyclerView.adapter = recetaAdapter // Reasignar el adaptador al RecyclerView

        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
            recyclerView.adapter?.notifyItemChanged(position) // Restaurar el elemento deslizado
        }
        builder.show()
    }

}
