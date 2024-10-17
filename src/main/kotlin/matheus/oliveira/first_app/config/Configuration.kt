package matheus.oliveira.first_app.config

import at.favre.lib.crypto.bcrypt.BCrypt
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class Configuration {

  @Bean
  fun bcryptPasswordEncode(): BCrypt.Hasher {
    return BCrypt.withDefaults()
  }

  @Bean
  fun bcryptPasswordVerifier(): BCrypt.Verifyer {
    return BCrypt.verifyer(BCrypt.Version.VERSION_2A)
  }
}