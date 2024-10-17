package matheus.oliveira.first_app.users.entities

import jakarta.persistence.*
import java.util.UUID


@Entity
@Table(name = "Users")
data class User(
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false)
  val id: UUID = UUID.randomUUID(),
  @Column(nullable = false)
  val name: String,
  @Column(unique = true, nullable = false)
  val email: String,
  @Column(nullable = false)
  val password: String
)


