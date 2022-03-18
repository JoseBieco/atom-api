package api.atomical.baseEntity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

data class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    @Column(name = "createdAt", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: LocalDateTime? = null,

    @Column(name = "updatedAt", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deletedAt", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var deletedAt: LocalDateTime? = null
)