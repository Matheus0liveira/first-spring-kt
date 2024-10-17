package matheus.oliveira.first_app.converters

import matheus.oliveira.first_app.libs.users.dto.ResponseUserDto
import matheus.oliveira.first_app.libs.users.entities.User
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class UserToResponseUserDtoConverter : Converter<User, ResponseUserDto> {
  override fun convert(source: User): ResponseUserDto {
    return ResponseUserDto(
      name = source.name,
      email = source.email
    )
  }
}