package matheus.oliveira.first_app.exception

import org.springframework.http.HttpStatus

class EmailNotExistsException(
  override val message: String = "Email Not exists",
  private val statusCode: HttpStatus
) : RuntimeException(message) {

  companion object {
    fun setException(message: String, statusCode: HttpStatus): EmailNotExistsException {

      return EmailNotExistsException(message, statusCode)
    }
  }

  fun getStatusCode(): HttpStatus {

    return statusCode
  }
}

