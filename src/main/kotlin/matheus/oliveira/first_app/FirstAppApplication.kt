package matheus.oliveira.first_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class FirstAppApplication

fun main(args: Array<String>) {
    runApplication<FirstAppApplication>(*args)
}
