package matheus.oliveira.first_app.exception

import org.springframework.http.HttpStatus

class Unauthorized(
  override val message: String = "JWT not provided",
  private val statusCode: HttpStatus = HttpStatus.UNAUTHORIZED
) : RuntimeException(message) {

  fun getStatusCode(): HttpStatus {
    return statusCode
  }
}

