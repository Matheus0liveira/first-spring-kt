package matheus.oliveira.first_app.converters

import matheus.oliveira.first_app.libs.users.dto.CreateUserDto
import matheus.oliveira.first_app.libs.users.entities.User
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CreateUserDtoToUserConverterDto : Converter<CreateUserDto, User> {
  override fun convert(source: CreateUserDto): User {
    return User(
      name = source.name,
      email = source.email,
      password = source.password,
    )
  }
}