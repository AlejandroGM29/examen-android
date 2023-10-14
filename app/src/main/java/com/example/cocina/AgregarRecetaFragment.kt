import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cocina.MainActivity
import com.example.cocina.R
import com.example.cocina.Receta


class AgregarRecetaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_agregar_receta, container, false)

        val editTextNombre: EditText = view.findViewById(R.id.editTextNombre)
        val editTextIngredientes: EditText = view.findViewById(R.id.editTextIngredientes)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)
        val btnAgregarReceta: Button = view.findViewById(R.id.btnAgregarReceta)
        btnAgregarReceta.setOnClickListener {
            mostrarDialogoConfirmacion()
        }

        return view
    }

    private fun mostrarDialogoConfirmacion() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirmar Envío")
        builder.setMessage("¿Estás seguro de que deseas enviar esta receta?")
        builder.setPositiveButton("Sí") { _, _ ->
            val editTextNombre: EditText = requireView().findViewById(R.id.editTextNombre)
            val editTextIngredientes: EditText = requireView().findViewById(R.id.editTextIngredientes)
            val ratingBar: RatingBar = requireView().findViewById(R.id.ratingBar)

            val nombreReceta = editTextNombre.text.toString()
            val ingredientesReceta = editTextIngredientes.text.toString()
            val calificacionReceta = ratingBar.rating

            if (nombreReceta.isNotEmpty() && ingredientesReceta.isNotEmpty()) {
                val nuevaReceta = Receta(nombreReceta, ingredientesReceta, calificacionReceta)

                // Agregar la nueva receta a la lista de recetas o a tu sistema de persistencia de datos
                (requireActivity() as MainActivity).agregarReceta(nuevaReceta)

                // Muestra un Toast para informar al usuario que la receta se ha agregado correctamente
                Toast.makeText(requireContext(), "Nueva receta agregada", Toast.LENGTH_SHORT).show()

                // Puedes regresar al fragmento anterior (ListaRecetasFragment) después de agregar la receta
                // En el Fragmento AgregarRecetaFragment
                requireActivity().supportFragmentManager.popBackStack()
            } else {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("No") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        builder.show()
    }
}
