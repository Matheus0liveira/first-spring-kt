package matheus.oliveira.first_app.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController()
@RequestMapping("/api/users")
class UserControler(@Autowired private val userRepository: UserRepository) {

  @GetMapping fun getAllUsers(): List<User> = userRepository.findAll().toList()

  @PostMapping
  fun createUser(@RequestBody user: User): ResponseEntity<User> {

    val savedUser = userRepository.save(user)
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

@RestController
@RequestMapping("/api/test")
class TestController {

  @GetMapping
  fun testEndpoint(): String {
    return "Hello, World!"
  }
}
