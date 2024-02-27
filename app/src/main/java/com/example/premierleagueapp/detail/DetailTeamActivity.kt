package com.example.premierleagueapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.premierleagueapp.R
import com.example.premierleagueapp.core.domain.model.Team
import com.example.premierleagueapp.databinding.ActivityDetailTeamBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTeamActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private  val detailViewModel: DetailViewModel by viewModel()
    private var binding: ActivityDetailTeamBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTeamBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)

        val detailTeam= intent.getParcelableExtra<Team>(EXTRA_DATA)
        showDetailTeam(detailTeam)


        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager = binding?.viewPager
        viewPager?.adapter = sectionsPagerAdapter
        val tabs = binding?.tabs
        TabLayoutMediator(tabs!!,viewPager!!){tab,position->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun showDetailTeam(detailTeam: Team?) {
        detailTeam?.let {
            supportActionBar?.title = detailTeam.strTeam
            Glide.with(this@DetailTeamActivity)
                .load(detailTeam.strTeamBadge)
                .into(binding?.ivDetailImage!!)

            var statusFavorite = detailTeam.isFavorite
            setStatusFavorite(statusFavorite)
            binding?.fab?.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteTeam(detailTeam, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }

    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding?.fab?.setImageResource(R.drawable.ic_favorite_white)
        } else {
            binding?.fab?.setImageResource(R.drawable.ic_not_favorite_white)
        }
    }


}