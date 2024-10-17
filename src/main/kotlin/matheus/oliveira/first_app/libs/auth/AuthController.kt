package matheus.oliveira.first_app.libs.auth

import jakarta.validation.Valid
import matheus.oliveira.first_app.libs.auth.dto.ResponseSignInDto
import matheus.oliveira.first_app.libs.auth.usecase.SignInUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController(private val signInUseCase: SignInUseCase) {

  @PostMapping
  fun auth(@Valid @RequestBody createAuthDto: CreateAuthDto): ResponseEntity<ResponseSignInDto> {

    val token = signInUseCase.exec(createAuthDto)

    return ResponseEntity(ResponseSignInDto(token), HttpStatus.CREATED)
  }

}

// TODO: Adicionar filtro para validar o token
// TODO: Apos verificar se o token é valido adicionar o userId no contexto usando thread local
// TODO: Olhar a possibilidade de identificar quais as rotas privadas e publicas
// https://gurselgazii.medium.com/enhancing-spring-boot-rest-apis-with-threadlocal-6c13eb63da8c