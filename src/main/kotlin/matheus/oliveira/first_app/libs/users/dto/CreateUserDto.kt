package matheus.oliveira.first_app.libs.users.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length


data class CreateUserDto(
  @field:NotBlank
  val name: String,

  @field:NotBlank
  @field:Email
  val email: String,

  @field:NotBlank
  @field:Length(min = 6)
  val password: String,
)