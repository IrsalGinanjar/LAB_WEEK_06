package com.example.lab_week_06.labweek6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AlertDialog
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val catAdapter by lazy {
        CatAdapter(layoutInflater, GlideImageLoader(this)) { cat ->
            showSelectionDialog(cat)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = catAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                catAdapter.removeAt(position)
            }
        })

        itemTouchHelper.attachToRecyclerView(recyclerView)

        catAdapter.setData(
            listOf(
                CatModel(Gender.Male, CatBreed.BalineseJavanese, "Fred", "Silent and deadly", "https://cdn2.thecatapi.com/images/7dj.jpg"),
                CatModel(Gender.Female, CatBreed.ExoticShorthair, "Wilma", "Cuddly assassin", "https://cdn2.thecatapi.com/images/egv.jpg"),
                CatModel(Gender.Unknown, CatBreed.AmericanCurl, "Curious George", "Award winning investigator", "https://cdn2.thecatapi.com/images/bar.jpg"),
                CatModel(Gender.Male, CatBreed.ExoticShorthair, "Tom", "Playful and curious", "https://cdn2.thecatapi.com/images/MTY3ODIyMQ.jpg"),
                CatModel(Gender.Female, CatBreed.BalineseJavanese, "Lucy", "Loves sleeping on sunny windowsills", "https://cdn2.thecatapi.com/images/4ki.jpg"),
                CatModel(Gender.Male, CatBreed.AmericanCurl, "Leo", "Always hungry, always cute", "https://cdn2.thecatapi.com/images/bpc.jpg"),
                CatModel(Gender.Female, CatBreed.ExoticShorthair, "Mia", "Queen of the house", "https://cdn2.thecatapi.com/images/8p0.jpg"),
                CatModel(Gender.Unknown, CatBreed.BalineseJavanese, "Shadow", "Mysterious and silent observer", "https://cdn2.thecatapi.com/images/9h9.jpg"),
                CatModel(Gender.Female, CatBreed.AmericanCurl, "Nala", "Loves to play with yarn", "https://cdn2.thecatapi.com/images/bpo.jpg"),
                CatModel(Gender.Male, CatBreed.ExoticShorthair, "Simba", "Brave little lion", "https://cdn2.thecatapi.com/images/d6i.jpg")
            )
        )
    }

    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            .setTitle("Cat Selected")
            .setMessage("You have selected cat ${cat.name}")
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }
}

