package com.example.premierleagueapp.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.premierleagueapp.core.ui.TeamAdapter
import com.example.premierleagueapp.favorite.databinding.FragmentFavoriteBinding
import com.example.premierleagueapp.detail.DetailTeamActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        if(activity!=null){
            val teamAdapter=TeamAdapter()
            teamAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailTeamActivity::class.java)
                intent.putExtra(DetailTeamActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.favoriteTeam.observe(viewLifecycleOwner) { dataTeam ->
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}