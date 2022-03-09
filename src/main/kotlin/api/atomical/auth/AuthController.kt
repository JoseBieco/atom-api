package api.atomical.auth

import api.atomical.auth.dto.LoginDto
import api.atomical.auth.dto.RegisterDto
import api.atomical.user.User
import api.atomical.user.dto.UserDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthController {
    /**
     * Register
     * Login
     * Logout
     */

    /**
     * Register a new user
     * @param user RegisterDto
     * @return User
     */
    @PostMapping
    @RequestMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody user: RegisterDto): User {
        return User(id = 1, name = "jose", email = "jo_bieco@hotmail.com", password = "123456789")
    }

    /**
     * Login route
     * @param user LoginDto
     * @return User
     */
    @PostMapping
    @RequestMapping("/login")
    fun login(@RequestBody user: LoginDto): User {
        return User(id = 1, name = "jose", email = "jo_bieco@hotmail.com", password = "123456789")
    }
}