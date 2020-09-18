package com.example.testesky.view

import MovieAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testesky.R
import com.example.testesky.model.MovieResult
import com.example.testesky.util.Constantes.ID
import com.example.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initVars()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    private fun initVars() {
        val movieList = mutableListOf<MovieResult>()
        val adapter = MovieAdapter(movieList) { movieResult -> clickMovie(movieResult) }
        val viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movies_recycler.adapter = adapter
        movies_recycler.layoutManager = GridLayoutManager(this, 2)
        viewModel.getMovies()
        viewModel.movieResult.observe(
            this,
            Observer { t -> adapter.updateList(t as MutableList<MovieResult>) })

        viewModel.loadingResult.observe(this, Observer {
            if (it == true) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
            }
        })

        viewModel.errorResult.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun clickMovie(movieResult: MovieResult) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(ID, movieResult)
        startActivity(intent)
        Toast.makeText(this, movieResult.title, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }
}