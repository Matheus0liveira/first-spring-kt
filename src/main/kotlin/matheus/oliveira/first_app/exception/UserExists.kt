package matheus.oliveira.first_app.exception

import org.springframework.http.HttpStatus

class UserExists(
  override val message: String = "Email/Password invalid",
  private val statusCode: HttpStatus
) : RuntimeException(message) {

  companion object {
    fun setException(message: String, statusCode: HttpStatus): UserExists {

      return UserExists(message, statusCode)
    }
  }

  fun getStatusCode(): HttpStatus {
    return statusCode
  }
}

