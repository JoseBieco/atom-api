package api.atomical.family

import api.atomical.baseEntity.BaseEntity
import api.atomical.family.dto.CreateFamilyDto
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "families")
class Family(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name= "name", nullable = false)
    var name: String? = null,

    @Column(name= "description", nullable = false)
    var description: String? = null,
): BaseEntity() {

    constructor(family: CreateFamilyDto): this() {
        this.apply {
            name = family.name
            description = family.description
            createdAt = LocalDateTime.now()
        }
    }
}