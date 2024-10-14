package matheus.oliveira.first_app.users

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "Users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        val id: UUID = UUID.randomUUID(),
        val name: String,
        val email: String
)
