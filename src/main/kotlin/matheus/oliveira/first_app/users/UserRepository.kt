package matheus.oliveira.first_app.users

import matheus.oliveira.first_app.users.entities.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, UUID> {

  fun findByEmail(email: String): Optional<User>
}
