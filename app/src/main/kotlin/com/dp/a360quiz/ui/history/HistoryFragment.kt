package com.dp.a360quiz.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dp.a360quiz.R
import com.dp.a360quiz.databinding.FragmentHistoryBinding
import com.dp.a360quiz.domain.usecase.wonGamesCount
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history) {

    private val historyAdapter = HistoryAdapter()

    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHistory.run {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }
        binding.ivBack.setOnClickListener { findNavController().popBackStack() }

        observeChanges()
    }

    private fun observeChanges() {
        viewModel.gameHistoryFlow
            .onEach { gameSessions ->
                historyAdapter.submitList(gameSessions)
                val gamesWon = "${gameSessions.wonGamesCount()}/${gameSessions.size}"
                binding.tvGamesWon.text = gamesWon
            }
            .launchIn(lifecycleScope)
    }

}