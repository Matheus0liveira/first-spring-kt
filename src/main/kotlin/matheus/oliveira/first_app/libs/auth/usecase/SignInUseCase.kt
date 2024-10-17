package matheus.oliveira.first_app.libs.auth.usecase

import at.favre.lib.crypto.bcrypt.BCrypt.Verifyer
import matheus.oliveira.first_app.libs.auth.CreateAuthDto
import matheus.oliveira.first_app.libs.auth.util.Jwt
import matheus.oliveira.first_app.exception.EmailNotExistsException
import matheus.oliveira.first_app.libs.users.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service


@Service
class SignInUseCase(private val userRepository: UserRepository, private val bcryptVerifier: Verifyer) {

  fun exec(body: CreateAuthDto): String {
    val existsUser = userRepository.findByEmail(body.email).orElseThrow {
      EmailNotExistsException(statusCode = HttpStatus.UNAUTHORIZED)
    }

    val hasValidPassword = bcryptVerifier.verify(
      body.password.toCharArray(), existsUser.password.toCharArray()
    ).verified

    if (!hasValidPassword) {
      throw EmailNotExistsException(statusCode = HttpStatus.UNAUTHORIZED)
    }

    return Jwt.generateToken(existsUser.id)
  }
}