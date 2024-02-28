package com.example.premierleagueapp.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.premierleagueapp.core.ui.TeamAdapter
import com.example.premierleagueapp.detail.DetailTeamActivity
import com.example.premierleagueapp.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Favorite Team"
        

        val teamAdapter= TeamAdapter()
        teamAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailTeamActivity::class.java)
            intent.putExtra(DetailTeamActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteTeam.observe(this) { dataTeam ->
            teamAdapter.setData(dataTeam)
            binding.viewEmpty.root.visibility = if (dataTeam.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvTeam) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = teamAdapter
        }
    }
}