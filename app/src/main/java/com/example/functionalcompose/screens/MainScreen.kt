package com.example.functionalcompose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.functionalcompose.ui.theme.Purple700

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    val title = remember { mutableStateOf("") }
    val appBarHorizontalPadding = 4.dp

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Purple700,
                elevation = 0.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                //TopAppBar Content
                Box(Modifier.height(32.dp)) {
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(72.dp - appBarHorizontalPadding),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CompositionLocalProvider(
                            LocalContentAlpha provides ContentAlpha.high,
                        ) {
                            IconButton(
                                onClick = { },
                                enabled = true,
                            ) {
                                Icon(Icons.Filled.Menu, null, tint = Color.White)
                            }
                        }
                    }

                    //Title
                    Row(
                        Modifier.fillMaxSize(),
                        verticalAlignment =
                        Alignment.CenterVertically
                    ) {
                        ProvideTextStyle(
                            value =
                            MaterialTheme.typography.h6
                        ) {
                            CompositionLocalProvider(
                                LocalContentAlpha provides
                                        ContentAlpha.high,
                            ) {
                                Text(
                                    modifier =
                                    Modifier.fillMaxWidth(),
                                    textAlign =
                                    TextAlign.Center,
                                    color = Color.White,
                                    maxLines = 1,
                                    text = title.value
                                )
                            }
                        }
                    }
                }
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            //reuse
            SnackbarHost(it) { data ->
                Snackbar(
                    actionColor = Color.Green,
                    contentColor = Color.White,
                    snackbarData = data
                )
            }
        },
    )
    { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavHost(
                navController = navController,
                startDestination = "pengelolaan-sampah"
        ) {
            composable("pengelolaan-sampah") {
                title.value = "Pengelolaan Sampah"
                PengelolaanSampahScreen(
                    navController =
                    navController, modifier = Modifier.padding(innerPadding)
                )
            }
            composable("tambah-pengelolaan-sampah") {
                title.value = "Tambah Pengelolaan Sampah"
                FormPencatatanSampahScreen(
                    navController =
                    navController, modifier = Modifier.padding(innerPadding)
                )
            }
            composable("edit-pengelolaan-sampah/{id}",
                listOf(
                    navArgument("id") {
                        type = NavType.StringType
                    }
                )) { backStackEntry ->
                title.value = "Edit Pengelolaan Sampah"
                val id =
                    backStackEntry.arguments?.getString("id")
                        ?: return@composable

                FormPencatatanSampahScreen(
                    navController =
                    navController, id, modifier =
                    Modifier.padding(innerPadding))
                }
            }
        }
    }
}
