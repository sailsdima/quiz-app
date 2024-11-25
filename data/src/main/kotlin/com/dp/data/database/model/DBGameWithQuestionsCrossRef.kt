package com.dp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.dp.data.database.TableNames.TABLE_GAME_QUESTIONS_CROSS_REF

/**
 * Represents the cross-reference between a game session and its associated questions.
 *
 * @property gameId The ID of the game session.
 * @property questionId The ID of the question.
 * @property addedAt The timestamp when the question was added to the game session.
 *
 * This table is used to maintain the many-to-many relationship between [DBGameSession] and [DBQuestion].
 * The `gameId` and `questionId` fields are foreign keys to their respective tables.
 */
@Entity(
    tableName = TABLE_GAME_QUESTIONS_CROSS_REF,
    primaryKeys = ["game_id", "question_id", "added_at"],
    indices = [
        Index("question_id"),
        Index("game_id")
    ],
    foreignKeys = [
        ForeignKey(
            entity = DBGameSession::class,
            parentColumns = ["g_id"],
            childColumns = ["game_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = DBQuestion::class,
            parentColumns = ["q_id"],
            childColumns = ["question_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ]
)
data class DBGameWithQuestionsCrossRef(
    @ColumnInfo(name = "game_id") val gameId: Long,
    @ColumnInfo(name = "question_id") val questionId: Long,
    @ColumnInfo(name = "added_at") val addedAt: Long,
)