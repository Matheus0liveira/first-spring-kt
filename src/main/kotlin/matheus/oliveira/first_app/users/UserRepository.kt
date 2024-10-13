package matheus.oliveira.first_app.users

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int>
