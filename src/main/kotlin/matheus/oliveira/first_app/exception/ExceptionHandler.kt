package matheus.oliveira.first_app.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException


data class ResponseError(
  val status: HttpStatus, val message: String? = "Internal Server Error",

  )

@RestControllerAdvice
class ExceptionHandler {


  @ExceptionHandler(Exception::class)
  fun handleException(ex: Exception): ResponseEntity<Any> {
    return ResponseEntity(
      ResponseError(status = HttpStatus.INTERNAL_SERVER_ERROR, message = ex.cause?.message),
      HttpStatus.INTERNAL_SERVER_ERROR
    )
  }

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleException(ex: MethodArgumentNotValidException): ResponseEntity<Any> {
    val errors = ex.bindingResult.fieldErrors.associate { it.field to (it.defaultMessage ?: "Invalid value") }
    return ResponseEntity(
      errors,
      HttpStatus.INTERNAL_SERVER_ERROR
    )
  }

  @ExceptionHandler(EmailNotExistsException::class)
  fun handleException(ex: EmailNotExistsException): ResponseEntity<Any> {
    val statusCode = ex.getStatusCode()
    return ResponseEntity.badRequest().body(
      ResponseError(
        status = statusCode,
        message = ex.message
      )
    )
  }

  @ExceptionHandler(UserExists::class)
  fun handleException(ex: UserExists): ResponseEntity<Any> {
    val statusCode = ex.getStatusCode()
    return ResponseEntity.badRequest().body(
      ResponseError(
        status = statusCode,
        message = ex.message
      )
    )
  }

  @ExceptionHandler(Unauthorized::class)
  fun handleException(ex: Unauthorized): ResponseEntity<Any> {
    val statusCode = ex.getStatusCode()
    return ResponseEntity.badRequest().body(
      ResponseError(
        status = statusCode,
        message = ex.message
      )
    )
  }
}