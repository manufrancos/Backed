package com.manuel.proyecto.controllers.error

import com.manuel.proyecto.exception.BaseException
import com.manuel.proyecto.model.ErrorData
import org.hibernate.exception.ConstraintViolationException
import org.springframework.beans.ConversionNotSupportedException
import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import org.springframework.stereotype.Controller
import org.springframework.validation.method.MethodValidationException
import org.springframework.web.ErrorResponseException
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingPathVariableException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.async.AsyncRequestTimeoutException
import org.springframework.web.method.annotation.HandlerMethodValidationException
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.multipart.support.MissingServletRequestPartException
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.servlet.resource.NoResourceFoundException

@ControllerAdvice
@Controller
class CustomError : ResponseEntityExceptionHandler() {


    @ExceptionHandler(Exception::class)
    fun handleCustomExceptions(ex: Exception, request: WebRequest): ResponseEntity<Any>? {
        val e : ErrorData
        val lang = request.getHeader("language")
        when (ex) {
            is BaseException -> {
                val er: BaseException = ex
                e = ErrorData(er.httpStatus.value(), er.devMsg, er.userMsg, starTrace = ex.message, optionData = er.optionalData)
                return ResponseEntity(e, er.httpStatus)
            }
            is ConstraintViolationException -> {
                val er: ConstraintViolationException = ex
                e = ErrorData(HttpStatus.BAD_REQUEST.value(), er.message)
                return ResponseEntity(e,HttpStatus.BAD_REQUEST)
            }
            else -> {
                e = ErrorData(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.localizedMessage)
                ex.printStackTrace()
                return ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest): ResponseEntity<Any> {
        val e = ErrorData(HttpStatus.NOT_ACCEPTABLE.value(), ex.bindingResult.toString())
        return ResponseEntity(e, HttpStatus.NOT_ACCEPTABLE)
    }

    override fun handleMissingPathVariable(ex: MissingPathVariableException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest): ResponseEntity<Any> {
        val e = ErrorData(HttpStatus.NOT_ACCEPTABLE.value(), ex.message)
        return ResponseEntity(e,HttpStatus.NOT_ACCEPTABLE)
    }

    override fun handleMissingServletRequestParameter(er: MissingServletRequestParameterException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest): ResponseEntity<Any> {
        val e = ErrorData(HttpStatus.NOT_ACCEPTABLE.value(), er.message)
        return ResponseEntity(e,HttpStatus.NOT_ACCEPTABLE)
    }

    override fun handleMissingServletRequestPart(ex: MissingServletRequestPartException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest): ResponseEntity<Any> {
        val e = ErrorData(HttpStatus.NOT_ACCEPTABLE.value(), ex.message)
        return ResponseEntity(e,HttpStatus.NOT_ACCEPTABLE)
    }

    override fun handleNoHandlerFoundException(ex: NoHandlerFoundException,headers: HttpHeaders,status: HttpStatusCode,request: WebRequest): ResponseEntity<Any> {
        val e = ErrorData(HttpStatus.NOT_ACCEPTABLE.value(), ex.message)
        return ResponseEntity(e,HttpStatus.NOT_FOUND)
    }

    override fun handleAsyncRequestTimeoutException(ex: AsyncRequestTimeoutException,headers: HttpHeaders,status: HttpStatusCode,request: WebRequest): ResponseEntity<Any>? {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

    override fun handleConversionNotSupported(ex: ConversionNotSupportedException,headers: HttpHeaders,status: HttpStatusCode,request: WebRequest): ResponseEntity<Any> {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

    override fun handleExceptionInternal(ex: Exception,body: Any?,headers: HttpHeaders,status: HttpStatusCode,request: WebRequest): ResponseEntity<Any> {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

    override fun handleHttpMediaTypeNotAcceptable(ex: HttpMediaTypeNotAcceptableException,headers: HttpHeaders,status: HttpStatusCode,request: WebRequest): ResponseEntity<Any> {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

    override fun handleHttpMediaTypeNotSupported(ex: HttpMediaTypeNotSupportedException,headers: HttpHeaders,status: HttpStatusCode,request: WebRequest): ResponseEntity<Any> {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException,headers: HttpHeaders,status: HttpStatusCode, request: WebRequest): ResponseEntity<Any> {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

    override fun handleHttpMessageNotWritable(ex: HttpMessageNotWritableException,headers: HttpHeaders,status: HttpStatusCode,request: WebRequest): ResponseEntity<Any> {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

    override fun handleHttpRequestMethodNotSupported(ex: HttpRequestMethodNotSupportedException,headers: HttpHeaders,status: HttpStatusCode,request: WebRequest): ResponseEntity<Any> {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

    override fun handleServletRequestBindingException(ex: ServletRequestBindingException,headers: HttpHeaders,status: HttpStatusCode,request: WebRequest): ResponseEntity<Any> {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

    override fun handleTypeMismatch(ex: TypeMismatchException,headers: HttpHeaders,status: HttpStatusCode,request: WebRequest): ResponseEntity<Any> {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

    override fun handleErrorResponseException(ex: ErrorResponseException,headers: HttpHeaders,status: HttpStatusCode,request: WebRequest): ResponseEntity<Any>? {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

    override fun handleHandlerMethodValidationException(ex: HandlerMethodValidationException,headers: HttpHeaders,status: HttpStatusCode,request: WebRequest): ResponseEntity<Any>? {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

    override fun handleMaxUploadSizeExceededException(ex: MaxUploadSizeExceededException,headers: HttpHeaders,status: HttpStatusCode,request: WebRequest): ResponseEntity<Any>? {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

    override fun handleMethodValidationException(ex: MethodValidationException,headers: HttpHeaders,status: HttpStatus,request: WebRequest): ResponseEntity<Any>? {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

    override fun handleNoResourceFoundException(ex: NoResourceFoundException,headers: HttpHeaders,status: HttpStatusCode,request: WebRequest): ResponseEntity<Any>? {
        val e = ErrorData(HttpStatus.BAD_REQUEST.value(), ex.message)
        return ResponseEntity(e,HttpStatus.BAD_REQUEST)
    }

}