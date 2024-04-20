package com.manuel.proyecto.exception

import org.springframework.http.HttpStatus


class BadRequest(userDes: String = "" ,  optionalData: Any? = null) : BaseException(httpStatus = HttpStatus.BAD_REQUEST,userMsg = userDes, optionalData = optionalData) {
}