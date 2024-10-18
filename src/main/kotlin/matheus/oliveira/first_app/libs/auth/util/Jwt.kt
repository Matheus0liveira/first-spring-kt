package matheus.oliveira.first_app.libs.auth.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import matheus.oliveira.first_app.exception.Unauthorized
import java.time.Instant
import java.util.*


object Jwt {
  private val algorithm: Algorithm = Algorithm.HMAC256("ANY_SECRET")
  private const val ISSUER = "matheus.oliveira.dev"

  fun generateToken(id: UUID): String {
    return JWT.create()
      .withIssuer(ISSUER)
      .withExpiresAt(Instant.now().plusMillis(259200000)) // 3 days
      .withClaim("userId", id.toString())
      .withIssuedAt(Instant.now())
      .sign(algorithm)
  }

  fun verifyToken(token: String): String {
    try {
      val result = JWT.require(algorithm).withIssuer(ISSUER).build().verify(token)

      return result.getClaim("userId").asString()
    } catch (e: Exception) {
      println("Error: ${e.localizedMessage}")

      throw Unauthorized()
    }
  }
}