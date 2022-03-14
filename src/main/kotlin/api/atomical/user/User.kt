package api.atomical.user

import api.atomical.auth.dto.RegisterDto
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime
import javax.persistence.*

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

    @Column(name = "deleted_at", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var deleted_at: LocalDateTime? = null,

    @Column(name = "token", nullable = true)
    var token: String? = null
){
    constructor(user: RegisterDto): this() {
        this.apply {
            name = user.name
            email = user.email
            password = user.password
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