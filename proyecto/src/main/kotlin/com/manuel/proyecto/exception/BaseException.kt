package com.manuel.proyecto.exception

import org.springframework.http.HttpStatus

open class BaseException(var httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR, val devMsg : String = "", val userMsg: String = "", var optionalData: Any? = null): RuntimeException() {

}