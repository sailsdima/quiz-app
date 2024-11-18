package com.dp.a360quiz.ui.history

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dp.a360quiz.databinding.ItemHistoryBinding
import com.dp.a360quiz.domain.usecase.GameSummaryStatus
import com.dp.a360quiz.domain.usecase.gameSummaryStatus
import com.dp.domain.model.GameSession
import com.dp.domain.model.correctAnswersCount
import com.dp.domain.model.incorrectAnswersCount
import com.dp.domain.model.score

private val diffCallback = object : DiffUtil.ItemCallback<GameSession>() {
    override fun areItemsTheSame(oldItem: GameSession, newItem: GameSession): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GameSession, newItem: GameSession): Boolean {
        return oldItem == newItem
    }
}

class HistoryAdapter : ListAdapter<GameSession, HistoryAdapter.HistoryViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(holder.adapterPosition))
    }

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) : ViewHolder(binding.root) {

        fun bind(gameSession: GameSession) = binding.run {
            binding.cardView.setCardBackgroundColor(
                if (gameSession.gameSummaryStatus() == GameSummaryStatus.WON) Color.parseColor("#449A31")
                else Color.parseColor("#FF889F")
            )
            tvGameStatus.text = gameSession.gameSummaryStatus().name
            tvFinishedAt.text = gameSession.finishedAt.toString()
            tvGameScore.text = gameSession.score().toString()
            tvQuestionsCount.text = gameSession.questionsCount.toString()
            tvCorrectAnswersCount.text = gameSession.correctAnswersCount().toString()
            tvWrongAnswersCount.text = gameSession.incorrectAnswersCount().toString()
            tvGameDuration.text = gameSession.gameDuration.toString()
        }
    }

}