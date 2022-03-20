package api.atomical.atom

import api.atomical.atom.dto.CreateAtomDto
import api.atomical.family.Family
import api.atomical.image.Image
import com.fasterxml.jackson.annotation.JsonFormat
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

    @Column(name = "number", nullable = false)
    var number: Int? = null,

    @Column(name = "weigh", nullable = false)
    var weigh: Float? = null,

    @Column(name = "symbol", nullable = false)
    var symbol: String? = null,

    @Column(name = "description", nullable = true)
    var description: String? = null,

    @Column(name = "curiosity", nullable = true)
    var curiosity: String? = null,

    @Column(name = "`column`", nullable = false)
    var column: Int? = null,

    @Column(name = "period", nullable = false)
    var period: Int? = null,

    @ManyToOne
    @JoinColumn(name = "familyId", nullable = true)
    var family: Family? = null,

    @OneToOne
    @JoinColumn(name = "imageId", nullable = true)
    var image: Image? = null,

    @OneToOne
    @JoinColumn(name = "atomImageId", nullable = true)
    var atomImage: Image? = null,

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
            number = atomDto.number
            weigh = atomDto.weigh
            symbol = atomDto.symbol
            description = atomDto.description
            curiosity = atomDto.curiosity
            column = atomDto.column
            period = atomDto.period
            createdAt = LocalDateTime.now()
        }
    }
}