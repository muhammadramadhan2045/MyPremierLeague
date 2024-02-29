package com.example.premierleagueapp.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.example.premierleagueapp.databinding.FragmentSettingBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingFragment : Fragment() {

    private  var _binding: FragmentSettingBinding?=null
    private val binding get() = _binding!!

    private val pref: SettingPreferences by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity !=null){

            val switchTheme=binding.switchTheme

            val settingViewModel: SettingViewModel by viewModel()

            settingViewModel.getThemeSetting().observe(viewLifecycleOwner){isDarkModeActive:Boolean ->
                if(isDarkModeActive){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    switchTheme.isChecked=true
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    switchTheme.isChecked=false
                }
            }

            switchTheme.setOnCheckedChangeListener { compoundButton, isChecked ->
                settingViewModel.saveThemeSetting(isChecked)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }





}