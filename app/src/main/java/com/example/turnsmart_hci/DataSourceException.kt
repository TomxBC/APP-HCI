package com.example.turnsmart_hci

class DataSourceException(
    var code: Int,
    message: String,
    var details: List<String>?
) : Exception(message)