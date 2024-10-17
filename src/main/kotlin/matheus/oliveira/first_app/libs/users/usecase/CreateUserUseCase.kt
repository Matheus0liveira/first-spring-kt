package matheus.oliveira.first_app.libs.users.usecase

import at.favre.lib.crypto.bcrypt.BCrypt.Hasher
import matheus.oliveira.first_app.exception.UserExists
import matheus.oliveira.first_app.libs.users.UserRepository
import matheus.oliveira.first_app.libs.users.dto.CreateUserDto
import matheus.oliveira.first_app.libs.users.dto.ResponseUserDto
import matheus.oliveira.first_app.libs.users.entities.User
import org.springframework.core.convert.ConversionService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service


@Service
class CreateUserUseCase(
  private val userRepository: UserRepository,
  val conversionService: ConversionService,
  val hasher: Hasher
) {

  fun exec(createUserDto: CreateUserDto): ResponseUserDto {
    val existingUser = userRepository.findByEmail(createUserDto.email)

    if (existingUser.isPresent) throw UserExists(statusCode = HttpStatus.UNAUTHORIZED)

    val encryptedPassword = hasher.hashToString(12, createUserDto.password.toCharArray())

    val savedUser = userRepository.save(
      conversionService.convert(
        createUserDto.copy(password = encryptedPassword.toString()), User::class.java
      )!!
    )
    return conversionService.convert(savedUser, ResponseUserDto::class.java)!!

  }
}