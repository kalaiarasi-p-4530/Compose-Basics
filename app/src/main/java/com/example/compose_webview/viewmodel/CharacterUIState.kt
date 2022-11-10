package com.example.compose_webview.viewmodel


import com.example.compose_webview.model.CharacterDataModel

data class  CharacterUIState  (val isLoading : Boolean = true,
                               val hasError : Boolean = false,
                               val errorMessage : String = "Something went wrong",
                               val characterList : List<CharacterDataModel> = listOf()
)