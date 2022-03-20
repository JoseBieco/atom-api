package api.atomical.atom

import api.atomical.atom.dto.CreateAtomDto
import api.atomical.family.Family
import api.atomical.image.Image
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "atoms")
class Atom(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "name", nullable = true)
    var name: String? = null,

    @ManyToOne
    @JoinColumn(name = "family_id", nullable = true)
    var family: Family? = null,

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

    constructor(atomDto: CreateAtomDto): this() {
        this.apply {
            name = atomDto.name
            createdAt = LocalDateTime.now()
        }
    }
}