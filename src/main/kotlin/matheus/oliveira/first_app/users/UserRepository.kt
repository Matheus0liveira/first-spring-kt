package matheus.oliveira.first_app.users

import matheus.oliveira.first_app.users.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {

  fun findByEmail(email: String): Optional<User>
}
