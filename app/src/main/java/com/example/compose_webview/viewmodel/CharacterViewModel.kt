package com.example.compose_webview.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.compose_webview.model.CharacterDataModel
import com.example.compose_webview.network.ApiRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.SocketTimeoutException

class CharacterViewModel : ViewModel() {

    private val _state: MutableStateFlow<CharacterUIState> = MutableStateFlow(CharacterUIState())
     val state: StateFlow<CharacterUIState> = _state


    fun fetchCharacterList() = flow<CharacterUIState> {
        try {
            _state.emit(state.value.copy(isLoading = true))

            val apiResponse = ApiRequest.callGetCharacters()
            if (apiResponse.isSuccessful) {
                val result = apiResponse.body() as List<CharacterDataModel>
                _state.emit(state.value.copy(characterList = result))
            } else {
                _state.emit(state.value.copy(hasError = true, errorMessage = "No records"))
            }

        } catch (e: IOException) {
            Log.d("exception", e.stackTraceToString())
            _state.emit(state.value.copy(hasError = true, errorMessage = "IO exception"))
        }
        catch (e: SocketTimeoutException) {
            Log.d("exception", e.stackTraceToString())
            _state.emit(state.value.copy(hasError = true, errorMessage = "SocketTimeoutException"))
        }
    }
}
