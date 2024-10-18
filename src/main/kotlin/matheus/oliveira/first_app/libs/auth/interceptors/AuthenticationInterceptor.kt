package matheus.oliveira.first_app.libs.auth.interceptors

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import matheus.oliveira.first_app.exception.Unauthorized
import matheus.oliveira.first_app.libs.auth.util.Jwt
import matheus.oliveira.first_app.libs.users.context.UserContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class AuthenticationInterceptor : HandlerInterceptor {

  override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
    val token = request.getHeader("Authorization")?.substringAfter("Bearer ")

    if (token.isNullOrEmpty()) throw Unauthorized()

    try {
      val userId = Jwt.verifyToken(token)
      UserContext.setUserId(userId)
      request.setAttribute("userId", userId)

      return true
    } catch (e: Exception) {
      println("Exception Error: ${e.message}")
      throw Unauthorized()
    }
  }

  override fun afterCompletion(
    request: HttpServletRequest,
    response: HttpServletResponse,
    handler: Any,
    ex: java.lang.Exception?
  ) {
    UserContext.clear()
  }
}
