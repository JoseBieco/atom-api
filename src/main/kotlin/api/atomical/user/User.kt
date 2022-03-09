package api.atomical.user

import javax.persistence.*

@Entity
@Table(name = "user")
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}