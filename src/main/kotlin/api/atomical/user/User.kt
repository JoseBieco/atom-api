package api.atomical.user

import api.atomical.auth.dto.RegisterDto
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name= "name", nullable = false)
    var name: String? = null,

    @Column(name= "email", nullable = false, unique = true)
    var email: String? = null,

    @Column(name= "password", nullable = false)
    @JsonIgnore
    var password: String? = null,

    @Column(name = "token", nullable = true)
    @JsonIgnore
    var token: String? = null,

    @Column(name = "roles", nullable = false)
    @JsonIgnore
    var roles: String? = "USER",

    @Column(name = "createdAt", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: LocalDateTime? = null,

    @Column(name = "updatedAt", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deletedAt", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var deletedAt: LocalDateTime? = null

){
    constructor(user: RegisterDto): this() {
        this.apply {
            name = user.name
            email = user.email
            password = user.password
            createdAt = LocalDateTime.now()
        }
    }

    /**
     * Encode user password
     * @param passwordEncoder PasswordEncoder
     * @return User model
     */
    fun encodePassword( passwordEncoder: PasswordEncoder): User {
        return this.apply { password =  passwordEncoder.encode(password) }
    }
}