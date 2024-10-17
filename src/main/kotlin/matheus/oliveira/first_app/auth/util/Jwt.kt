package matheus.oliveira.first_app.auth.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import jakarta.persistence.Temporal
import java.time.Instant
import java.util.*


object Jwt {
  private val algorithm: Algorithm = Algorithm.HMAC256("ANY_SECRET")

  fun generateToken(id: UUID): String {
    return JWT.create()
      .withIssuer("matheus.oliveira.dev")
      .withExpiresAt(Instant.now().plusMillis(259200000))
      .withClaim("userId", id.toString())
      .withIssuedAt(Instant.now())
      .sign(algorithm)
  }

  fun verifyToken(token: String): String {
    return JWT.require(algorithm).withIssuer("matheus.oliveira.dev").build().verify(token).id

  }
}