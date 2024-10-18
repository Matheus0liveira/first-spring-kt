package matheus.oliveira.first_app.libs.auth.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import matheus.oliveira.first_app.libs.auth.interceptors.AuthenticationInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(private val authInterceptor: AuthenticationInterceptor) : WebMvcConfigurer {

  override fun addInterceptors(registry: InterceptorRegistry) {
    registry.addInterceptor(object : HandlerInterceptor {
      override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
      ): Boolean {

        return if (request.method == "GET" && request.requestURI.startsWith("/api/users")) {
          authInterceptor.preHandle(request, response, handler)
        } else true
      }

      override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
      ) {
        authInterceptor.afterCompletion(request, response, handler, ex)
      }
    })
  }
}
