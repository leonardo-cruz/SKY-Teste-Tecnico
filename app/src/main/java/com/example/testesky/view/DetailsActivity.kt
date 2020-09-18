package com.example.testesky.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.testesky.R
import com.example.testesky.model.MovieResult
import com.example.testesky.util.Constantes.ID
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        getDetails()
    }

    private fun getDetails() {
        try {
            val movie = intent.getSerializableExtra(ID) as MovieResult
            Picasso.get().load(movie.cover_url).into(iv_details)
            tv_title.text = movie.title
            tv_duration.text = movie.duration
            tv_release.text = movie.release_year
            tv_overview.text = movie.overview
        } catch (e: Exception) {
            Toast.makeText(this, getString(R.string.erro_details), Toast.LENGTH_LONG).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
        return true
    }

}