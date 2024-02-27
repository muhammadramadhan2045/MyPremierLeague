package com.example.premierleagueapp.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.premierleagueapp.R
import com.example.premierleagueapp.core.domain.model.Team
import com.example.premierleagueapp.databinding.FragmentDescriptionBinding
import com.example.premierleagueapp.detail.DetailTeamActivity.Companion.EXTRA_DATA
import org.koin.androidx.viewmodel.ext.android.viewModel


class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!

    val detailViewModel: DetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailTeam = activity?.intent?.getParcelableExtra<Team>(EXTRA_DATA)

        binding.tvDescription.text = detailTeam?.strDescriptionEN

    }

}