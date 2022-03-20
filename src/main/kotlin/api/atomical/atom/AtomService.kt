package api.atomical.atom

import api.atomical.atom.dto.CreateAtomDto
import api.atomical.family.FamilyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class AtomService(
    @Autowired
    val db: AtomRepository,

    @Autowired
    val familyDb: FamilyRepository
) {

    /**
     * Search the family, save the new atom and then insert the atom on family atoms list
     * @param familyId Represents the family unique key
     * @param atomDto Data to create the atom
     * @return Created Atom
     */
    fun create(familyId: Long, atomDto: CreateAtomDto): Atom {
        val atomFamily = familyDb.getById(familyId)
        val atom = Atom(atomDto).apply {family = atomFamily }.run { db.save(this) }

        atomFamily.apply {
            updatedAt = LocalDateTime.now()
            atoms?.add(atom)
        }

        return atom
    }

    /**
     * Get all atoms pageable
     */
    fun getAll(pageRequest: PageRequest): Page<Atom> {
        return db.getAll(pageRequest)
    }

    /**
     * Get atom by id
     * @param atomId Represents the atom unique key
     * @return The required atom
     */
    fun getById(atomId: Long): Atom {
        return db.findById(atomId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Family not found.")
        }
    }
}