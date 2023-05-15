package bes.max.moviesearcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import bes.max.moviesearcher.model.Movie
import bes.max.moviesearcher.model.MovieResponse
import bes.max.moviesearcher.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var button: Button
    val listOfMovies = mutableListOf<Movie>()
    private val adapter = MovieListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view_main_activity)
        editText = findViewById(R.id.edit_text_main_activity)
        button = findViewById(R.id.button_main_activity)

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
        MovieApi.movieRetrofitService.getMovies(userInput).enqueue(object: Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.code() == 200) {
                    listOfMovies.clear()
                    adapter.notifyDataSetChanged()
                    if (response.body()?.results?.isNotEmpty() == true) {
                        listOfMovies.addAll(response.body()!!.results)
                        adapter.notifyDataSetChanged()
                    }
                    if (response.body()?.results?.isEmpty() == true) {
                        Toast.makeText(applicationContext, "По запросу ничего не найдено", Toast.LENGTH_LONG)
                    }
                } else {
                    listOfMovies.clear()
                    adapter.notifyDataSetChanged()
                    Toast.makeText(applicationContext, "Что-то пошло не так, попробуйте ещё раз", Toast.LENGTH_LONG)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                listOfMovies.clear()
                adapter.notifyDataSetChanged()
                Toast.makeText(applicationContext, "Ошибка: ${t.toString()}", Toast.LENGTH_LONG)            }

        })
    }
}