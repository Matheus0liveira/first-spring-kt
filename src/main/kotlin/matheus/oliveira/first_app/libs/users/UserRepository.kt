package matheus.oliveira.first_app.libs.users

import matheus.oliveira.first_app.libs.users.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {

  fun findByEmail(email: String): Optional<User>
}
