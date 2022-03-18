package api.atomical.family

import api.atomical.baseEntity.BaseEntity
import api.atomical.family.dto.CreateFamilyDto
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "families")
class Family(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name= "name", nullable = false, unique = true)
    var name: String? = null,

    @Column(name= "description", nullable = false)
    var description: String? = null,

    @Column(name = "createdAt", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: LocalDateTime? = null,

    @Column(name = "updatedAt", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deletedAt", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var deletedAt: LocalDateTime? = null,

) {
    constructor(family: CreateFamilyDto): this() {
        this.apply {
            name = family.name
            description = family.description
            createdAt = LocalDateTime.now()
        }
    }
}