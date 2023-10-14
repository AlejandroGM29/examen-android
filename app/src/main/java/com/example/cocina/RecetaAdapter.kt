package com.example.cocina

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cocina.R

class RecetaAdapter(private val listaRecetas: MutableList<Receta>) : RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>() {

    // Interfaz para manejar eventos de clic en los elementos del RecyclerView
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    fun eliminarReceta(position: Int) {
        listaRecetas.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    class RecetaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val favoritoCheckBox: CheckBox = itemView.findViewById(R.id.favoritoCheckBox)
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val ingredientesTextView: TextView = itemView.findViewById(R.id.ingredientesTextView)
        val calificacionTextView: TextView = itemView.findViewById(R.id.calificacionTextView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_receta, parent, false)
        return RecetaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        val currentReceta = listaRecetas[position]
        holder.nombreTextView.text = currentReceta.nombre
        holder.ingredientesTextView.text = currentReceta.ingredientes
        holder.calificacionTextView.text = "Calificación: ${currentReceta.rating}/5" // Mostrar la calificación
        holder.favoritoCheckBox.isChecked = currentReceta.esFavorita

        // Configurar el evento de cambio de estado del CheckBox
        holder.favoritoCheckBox.setOnCheckedChangeListener { _, isChecked ->
            currentReceta.esFavorita = isChecked
        }

        // Configurar el evento de clic en el elemento del RecyclerView
        holder.itemView.setOnClickListener {
            listener?.onItemClick(position)
        }
    }


    override fun getItemCount() = listaRecetas.size
}
