package com.example.compose_webview.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_webview.model.CharacterDataModel
import com.example.compose_webview.network.ApiRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

class CharacterViewModel : ViewModel() {
/*

    private val _state: MutableStateFlow<CharacterUIState> = MutableStateFlow(CharacterUIState())
     val state : StateFlow<CharacterUIState> = _state
*/


     private val _itemList = mutableStateOf<CharacterUIState>(CharacterUIState())
    val itemList : State<CharacterUIState> = _itemList


   /* private val _state = mutableStateOf<CharacterUIState>(CharacterUIState())
    val state: CharacterUIState
        get() = _state.value*/

    fun fetchCharacterList() {
        viewModelScope.launch {
            try {
               // _state.emit(state.value.copy(isLoading = true))
                _itemList.value = _itemList.value.copy(isLoading = true)
                val apiResponse = ApiRequest.getRetrofit().getCharacters()
                if (apiResponse.isSuccessful) {
                    _itemList.value = _itemList.value.copy(hasError = true, errorMessage = "No records")
                    val result = apiResponse.body() as List<CharacterDataModel>
                    for (i in 0 until result.size){
                        Log.d("Test111", result[i].name)
                    }

                //    _state.emit(state.value.copy(characterList = result))
                    _itemList.value= _itemList.value.copy(characterList = result)
                } else {
                //    _state.emit(state.value.copy(hasError = true, errorMessage = "No records"))
                    _itemList.value = _itemList.value.copy(hasError = true, errorMessage = "No records")
                }

            } catch (e: IOException) {
                Log.d("exception", e.stackTraceToString())
               // _state.emit(state.value.copy(hasError = true, errorMessage = "IO exception"))
                _itemList.value = _itemList.value.copy(hasError = true, errorMessage = "IO exception")
            }
            catch (e: SocketTimeoutException) {
                Log.d("exception", e.stackTraceToString())
             //   _state.emit(state.value.copy(hasError = true, errorMessage = "SocketTimeoutException"))
                _itemList.value = _itemList.value.copy(hasError = true, errorMessage = "SocketTimeoutException")
            }
        }

    }
}
