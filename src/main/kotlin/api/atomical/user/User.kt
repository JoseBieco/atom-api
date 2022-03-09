package api.atomical.user

import api.atomical.auth.dto.RegisterDto
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

/**
 * Just used to represent the entity
 */
@Entity
@Table(name = "user")
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
    var password: String? = null
){
    constructor(user: RegisterDto): this() {
        this.apply {
            name = user.name
            email = user.email
            password = user.password
        }
    }
}