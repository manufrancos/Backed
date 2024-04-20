package com.manuel.proyecto.model

import java.time.ZonedDateTime

data class ErrorData(
        var errorCode: Int = 0,
        val errorDes: String? = null,
        val errorUser: String? = null,
        val date: ZonedDateTime = ZonedDateTime.now(),
        val starTrace: String? = null,
        val optionData: Any? = null
)
