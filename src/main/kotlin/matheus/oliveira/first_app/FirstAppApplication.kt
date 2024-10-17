package matheus.oliveira.first_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class FirstAppApplication : WebMvcConfigurer

fun main(args: Array<String>) {
  runApplication<FirstAppApplication>(*args)

}

