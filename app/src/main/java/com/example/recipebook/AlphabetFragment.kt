package com.example.recipebook

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class AlphabetFragment : Fragment() {

    private val recipeData = mapOf(
        "A" to listOf("Ayam Goreng", "Ayam Bakar", "Ayam Kecap"),
        "B" to listOf("Bebek Goreng", "Bebek Bakar", "Bebek Kecap"),
        "C" to listOf("Cumi Goreng", "Cumi Bakar", "Cumi Asam Manis"),
        "D" to listOf("Daging Goreng", "Daging Bakar", "Daging Tumis"),
        "E" to listOf("Es Teh", "Es Jeruk", "Es Campur"),
        "F" to listOf("Fried Chicken", "French Fries", "Fish and Chips"),
        "G" to listOf("Gado-gado", "Gulai", "Gudeg"),
        "H" to listOf("Hamburger", "Hot Dog", "Hokkien Mee"),
        "I" to listOf("Ice Cream", "Ikan Bakar", "Ikan Goreng"),
        "J" to listOf("Jus Alpukat", "Jus Apel", "Jus Mangga")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_alphabet, container, false)
        val alphabetListView: ListView = view.findViewById(R.id.alphabetListView)

        val alphabetList = ('A'..'J').map { it.toString() }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, alphabetList)
        alphabetListView.adapter = adapter

        alphabetListView.setOnItemClickListener { parent, _, position, _ ->
            val selectedAlphabet = parent.getItemAtPosition(position) as String
            val recipes = recipeData[selectedAlphabet] ?: emptyList()
            showRecipeDialog(recipes)
        }

        return view
    }

    private fun showRecipeDialog(recipes: List<String>) {
        val recipeNames = recipes.toTypedArray()
        AlertDialog.Builder(requireContext())
            .setTitle("Choose a Recipe")
            .setItems(recipeNames) { _, which ->
                val selectedRecipe = recipeNames[which]
                val url = "https://www.google.com/search?q=${selectedRecipe.replace(" ", "+")}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
            .show()
    }
}
