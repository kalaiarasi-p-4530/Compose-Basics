package com.example.compose_webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.compose_webview.ui.theme.ComposeWebviewTheme
import com.example.compose_webview.viewmodel.CharacterUIState
import com.example.compose_webview.viewmodel.CharacterViewModel

class CharacterActivity : ComponentActivity(){
    val viewModel: CharacterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWebviewTheme{
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    Content(viewModel.itemList.value)

                    LaunchedEffect(key1 = Unit, block = {
                        viewModel.fetchCharacterList()
                    })
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content(state: CharacterUIState) {


        Scaffold(topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Character")
                },
                Modifier.background(MaterialTheme.colorScheme.background),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                }
            )
        }, content = {

            CharacterList(state)
        }, floatingActionButton = {
            Fab()
        })
    }

     @SuppressLint("StateFlowValueCalledInComposition")
     @Composable
    fun CharacterList(state: CharacterUIState) {
         LazyColumn{
             items(state.characterList){item->
                 Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                     item.let { uiState ->
                         Column(verticalArrangement = Arrangement.Top) {
                             Box(modifier = Modifier.fillMaxSize()){
                                 Column(verticalArrangement = Arrangement.Top) {
                                     Text(text =uiState.name)
                                     Text(text =uiState.name)
                                     Text(text =uiState.name)
                                 }
                             }
                         }

                     }
                 }
             }

         }
    // if there is an error loading the report
         if (state.hasError) {
             Toast.makeText(LocalContext.current, "ERRRR...", Toast.LENGTH_SHORT).show()
             Text(
                 text = state.errorMessage ?: "Something went wrong",
                 style = TextStyle(
                     color = Color.DarkGray,
                     fontSize = 40.sp,
                     textAlign = TextAlign.Center
                 )
             )
         }

         if (state.isLoading) {
             Toast.makeText(LocalContext.current, "Loading...", Toast.LENGTH_SHORT).show()
         }
    }
}