package com.reyaz.notesmongodb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.reyaz.notesmongodb.presentation.addnote.AddNoteScreen
import com.reyaz.notesmongodb.presentation.addnote.AddNotesScreen
import com.reyaz.notesmongodb.presentation.addnote.ViewModelAddNotes
import com.reyaz.notesmongodb.presentation.home.HomeScreen
import com.reyaz.notesmongodb.presentation.home.ViewModelHome
import com.reyaz.notesmongodb.ui.theme.NotesMongoDbTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesMongoDbTheme {
                val navController: NavHostController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    floatingActionButton = {
                        if (currentDestination?.hasRoute(HomeScreen::class) == true) {
                            FloatingActionButton(
                                onClick = { navController.navigate(AddNotesScreen) }
                            ) {
                                Icon(Icons.Default.Add, "Add Notes btn")
                            }
                        }
                    }

                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        startDestination = HomeScreen
                    ) {
                        composable<HomeScreen> {
                            val viewModel = hiltViewModel<ViewModelHome>()
                            val state by viewModel.stateNote.collectAsState()
                            HomeScreen(
                                state = state,
                                navController = navController
                            )
                        }
                        composable<AddNotesScreen> {
                            val viewModel: ViewModelAddNotes = hiltViewModel()
                            val state by viewModel.notesState.collectAsState()
                            AddNoteScreen(
                                navController = navController,
                                state = state,
                                event = {
                                    viewModel.onEvent(it)
                                }
                            )
                        }

                    }
                }
            }
        }
    }
}