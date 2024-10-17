package matheus.oliveira.first_app.users

import jakarta.validation.Valid
import matheus.oliveira.first_app.users.entities.User
import org.springframework.core.convert.ConversionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController()
@RequestMapping("/api/users")
class UserController(private val userRepository: UserRepository, val conversionService: ConversionService) {


  @GetMapping
  fun getAllUsers(): List<User> = userRepository.findAll().toList()

  @PostMapping
  fun createUser(@Valid @RequestBody createUserDto: CreateUserDto): ResponseEntity<User> {


    val savedUser = userRepository.save(conversionService.convert(createUserDto, User::class.java)!!)
    return ResponseEntity(savedUser, HttpStatus.CREATED)
  }

  @GetMapping("/{id}")
  fun getUserById(@PathVariable("id") id: UUID): ResponseEntity<User> {
    val existingUser = userRepository.findById(id).orElse(null)

    return if (existingUser != null) {
      ResponseEntity(existingUser, HttpStatus.FOUND)
    } else {
      ResponseEntity(HttpStatus.NOT_FOUND)
    }
  }

  @PatchMapping("/{id}")
  fun updateUserById(@PathVariable("id") id: UUID, @RequestBody user: User): ResponseEntity<User> {
    val existingUser = userRepository.findById(id).orElse(null) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

    val updatedUser = existingUser.copy(name = user.name, email = user.email)
    userRepository.save(updatedUser)
    return ResponseEntity(user, HttpStatus.OK)
  }

  @DeleteMapping("/{id}")
  fun deleteUserById(@PathVariable("id") id: UUID): ResponseEntity<Unit> {

    val existingUser = userRepository.findById(id).orElse(null) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

    userRepository.delete(existingUser)

    return ResponseEntity(HttpStatus.NO_CONTENT)
  }
}
