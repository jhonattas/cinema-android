package br.com.patrocine.patrocine.views.activities

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.patrocine.patrocine.R
import br.com.patrocine.patrocine.model.Movie
import br.com.patrocine.patrocine.views.fragments.MapFragment
import br.com.patrocine.patrocine.views.fragments.MoviesFragment
import br.com.patrocine.patrocine.views.fragments.PromoFragment
import br.com.patrocine.patrocine.views.fragments.SocialFragment
import br.com.patrocine.patrocine.views.interfaces.OnFragmentInteractionListener
import br.com.patrocine.patrocine.views.interfaces.OnMovieClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener, OnMovieClickListener {

    override fun onMovieClick(movie: Movie) {
        Toast.makeText(this, "cliquei no item" + movie.title, Toast.LENGTH_SHORT).show();
    }

    override fun onFragmentInteraction(uri: Uri?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Compartilhamento nao disponivel", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val fragmentManager = supportFragmentManager
        when (item.itemId) {

            R.id.nav_news -> {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, PromoFragment.newInstance("", ""))
                        .commit()
            }
            R.id.nav_sessions -> {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, MoviesFragment.newInstance("", ""))
                        .commit()
            }
            R.id.nav_contact -> {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, SocialFragment.newInstance("", ""))
                        .commit()
            }
            R.id.nav_map -> {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, MapFragment.newInstance("", ""))
                        .commit()

            }
            R.id.nav_share -> {
                Toast.makeText(this, "Compartilhamento", Toast.LENGTH_SHORT).show();
            }
            R.id.nav_send -> {
                Toast.makeText(this, "Clicou em enviar", Toast.LENGTH_SHORT).show();
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
