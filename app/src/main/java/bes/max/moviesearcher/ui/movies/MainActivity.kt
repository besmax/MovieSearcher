package bes.max.moviesearcher.ui.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bes.max.moviesearcher.R
import bes.max.moviesearcher.domain.models.Movie
import bes.max.moviesearcher.domain.api.MoviesRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var button: Button
    private var listOfMovies = mutableListOf<Movie>()
    private val adapter = MovieListAdapter()
    @Inject
    lateinit var movieRepository: MoviesRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recyclerView = findViewById(R.id.recycler_view_main_activity)
//        editText = findViewById(R.id.edit_text_main_activity)
//        button = findViewById(R.id.button_movies_screen)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        button.setOnClickListener {
            if (editText.text.isNotEmpty()) {
                getMovies(editText.text.toString())
                adapter.movies = listOfMovies
                adapter.notifyDataSetChanged()
            }
            val inputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            val view = this.currentFocus
            if (view != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
            }
        }
    }

    private fun getMovies(userInput: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            listOfMovies = movieRepository.getMovies(userInput).toMutableList()
        }
    }
}