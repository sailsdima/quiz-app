package com.dp.a360quiz.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dp.a360quiz.R
import com.dp.a360quiz.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStartGame.setOnClickListener { findNavController().navigate(R.id.toQuizFragment) }
        binding.btnHistory.setOnClickListener { findNavController().navigate(R.id.toHistoryFragment) }
        observeChanges()
    }

    private fun observeChanges() {
        viewModel.currentScoreFlow
            .onEach { score -> binding.tvCurrentScore.text = score.toString() }
            .launchIn(lifecycleScope)
    }

}