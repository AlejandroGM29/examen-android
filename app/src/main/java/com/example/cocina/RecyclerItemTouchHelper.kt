package com.example.cocina

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cocina.RecyclerItemTouchHelperListener

class RecyclerItemTouchHelper(
    context: Context,
    private val listener: RecyclerItemTouchHelperListener
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        // No permitir el movimiento de elementos en el RecyclerView
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        listener.showDeleteConfirmationDialog(position)
    }
}
