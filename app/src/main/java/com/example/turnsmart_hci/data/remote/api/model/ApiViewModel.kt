package com.example.turnsmart_hci.data.remote.api.model

//Importar el jsonAnswerClass

sealed interface  ApiState {
    //data class Success(val answer: ) : ApiState
    data object Error: ApiState
    data object Loading: ApiState
}

// A partir de este estado puedo manejar lo que sucede en cada screen (when / is)