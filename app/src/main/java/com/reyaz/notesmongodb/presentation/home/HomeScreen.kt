package com.reyaz.notesmongodb.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.reyaz.notesmongodb.domain.models.Notes
import kotlinx.serialization.Serializable


@Serializable
data object HomeScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: StateHomeScreen,
) {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            if (isNotesFetching(state))
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = modifier.fillMaxSize(),
                    contentPadding = PaddingValues(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalItemSpacing = 8.dp
                ) {
                    items(state.fetchedNotes!!) { notes ->
                        NotesCard(notes = notes)
                    }
                }
        }
    }

}

fun isNotesFetching(state: StateHomeScreen): Boolean {
    return when {
        state.gettingNotes -> false
        state.fetchedNotes!!.isNotEmpty() -> true
        else -> false
    }
}

@Composable
fun NotesCard(
    modifier: Modifier = Modifier,
    notes: Notes
) {
    val chipColor = remember(notes.label) {
        when (notes.label) {
            "Low" -> Color.Red
            "High" -> Color.Green
            else -> Color.Yellow
        }
    }
    Box(Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(chipColor.copy(alpha = 0.1f)),
            elevation = CardDefaults.cardElevation(10.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column (
                modifier = Modifier.padding(8.dp)
            ){
                Text(
                    notes.title,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis
                )
                Text(notes.description, style = MaterialTheme.typography.labelLarge)
                Text(
                    notes.date,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Right
                )
            }

        }
    }
}