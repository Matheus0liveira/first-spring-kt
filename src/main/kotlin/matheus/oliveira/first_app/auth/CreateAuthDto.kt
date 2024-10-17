package matheus.oliveira.first_app.auth

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CreateAuthDto(

  @field:Email
  val email: String,

  @field:NotBlank
  val password: String
)

