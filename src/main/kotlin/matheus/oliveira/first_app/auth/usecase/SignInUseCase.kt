package matheus.oliveira.first_app.auth.usecase

import at.favre.lib.crypto.bcrypt.BCrypt.Verifyer
import matheus.oliveira.first_app.auth.CreateAuthDto
import matheus.oliveira.first_app.auth.util.Jwt
import matheus.oliveira.first_app.exception.EmailNotExistsException
import matheus.oliveira.first_app.users.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service


@Service
class SignInUseCase(private val userRepository: UserRepository, private val bcryptVerifier: Verifyer) {

  fun exec(body: CreateAuthDto): String {
    val existsUser = userRepository.findByEmail(body.email)

    if (existsUser.isEmpty) throw EmailNotExistsException(statusCode = HttpStatus.UNAUTHORIZED)


    val hasValidPassword = bcryptVerifier.verify(
      body.password.toCharArray(), existsUser.get().password.toCharArray()
    ).verified

    if (!hasValidPassword) throw EmailNotExistsException(statusCode = HttpStatus.UNAUTHORIZED)

    return Jwt.generateToken(existsUser.get().id)
  }
}