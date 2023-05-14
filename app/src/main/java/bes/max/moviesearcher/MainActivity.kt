package bes.max.moviesearcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import bes.max.moviesearcher.network.MovieApi

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var editText: EditText
    lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view_main_activity)
        editText = findViewById(R.id.edit_text_main_activity)
        button = findViewById(R.id.button_main_activity)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = MovieListAdapter()

        button.setOnClickListener {
            if (editText.text.isNotEmpty()) {

            }
        }

        MovieApi.movieRetrofitService
    }
}