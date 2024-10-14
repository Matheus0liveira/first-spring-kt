package matheus.oliveira.first_app.users

import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import io.github.serpro69.kfaker.Faker
import org.hamcrest.Matchers.*
import org.springframework.http.HttpStatus
import java.util.UUID

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UserControllerE2ETest {

    @LocalServerPort
    var port: Int = 0
    lateinit var faker: Faker
    lateinit var makeUser: User

    @BeforeEach
    fun setUp() {
        faker = Faker()
        RestAssured.port = port
        makeUser = User(
            name = faker.name.nameWithMiddle(),
            email = faker.internet.email()
        )
    }


    @Test
    fun `should create a new user`() {
        val requestBody = mapOf(
            "name" to makeUser.name,
            "email" to makeUser.email
        )

        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .`when`()
            .post("/api/users")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("name", equalTo(requestBody["name"]))
            .body("email", equalTo(requestBody["email"]))
    }

    @Test
    fun `should get all users`() {
        RestAssured.given()
            .contentType(ContentType.JSON)
            .`when`()
            .get("/api/users")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("size()", greaterThan(0))
    }

    @Test
    fun `should get a unique user by id`() {

        val createdUser = createUser()
        RestAssured.given()
            .contentType(ContentType.JSON)
            .`when`()
            .get("/api/users/${createdUser.id}")
            .then()
            .statusCode(HttpStatus.FOUND.value())
            .body("id", any(String::class.java))
            .body("name", equalTo(createdUser.name))
            .body("email", equalTo(createdUser.email))
    }

    @Test
    fun `should delete user by id`() {

        RestAssured.given()
            .contentType(ContentType.JSON)
            .`when`()
            .delete("/api/users/${createUser().id}")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }


    private fun createUser(): User{

        val requestBody = mapOf(
            "name" to makeUser.name,
            "email" to makeUser.email
        )
       return  RestAssured.given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .`when`()
            .post("/api/users")
            .`as`(User::class.java)
    }
}
