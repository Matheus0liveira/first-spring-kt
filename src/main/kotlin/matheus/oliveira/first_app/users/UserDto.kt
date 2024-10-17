package matheus.oliveira.first_app.users

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank


data class CreateUserDto(
  @field:NotBlank
  val name: String,

  @field:NotBlank
  @field:Email(message = "Email inválido")
  val email: String,
)