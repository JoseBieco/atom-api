package api.atomical.atom

import api.atomical.family.Family
import api.atomical.image.Image
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table


@Entity
@Table(name = "atoms")
class Atom(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "family_id")
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
}