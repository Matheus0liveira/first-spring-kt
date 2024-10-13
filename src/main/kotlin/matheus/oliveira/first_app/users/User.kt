package matheus.oliveira.first_app.users

import jakarta.persistence.*

@Entity
@Table(name = "Users")
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int,
        val name: String,
        val email: String
)
