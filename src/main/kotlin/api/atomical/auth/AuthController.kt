package api.atomical.auth

import api.atomical.auth.dto.LoginDto
import api.atomical.auth.dto.RegisterDto
import api.atomical.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthController(
    @Autowired
    val service: AuthService
) {
    /**
     * Register
     * Login
     * Logout
     * Delete
     */

    /**
     * Register a new user
     * @param user RegisterDto
     * @return User
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@Validated @RequestBody user: RegisterDto): User {
        return service.create(user)
    }

    /**
     * Login route
     * @param user LoginDto
     * @return User
     */
    @PostMapping("/login")
    fun login(@RequestBody user: LoginDto): User {
        return User(id = 1, name = "jose", email = "jo_bieco@hotmail.com", password = "123456789")
    }

    /**
     * Delete user from database
     */
    @DeleteMapping("/{userId}")
    fun delete(@PathVariable userId: Long) {
        return service.delete(userId)
    }
}