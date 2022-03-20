package api.atomical.atom

import api.atomical.atom.dto.CreateAtomDto
import api.atomical.atom.dto.UpdateAtomDto
import api.atomical.atom.dto.UpdateAtomImagesDto
import api.atomical.family.FamilyService
import api.atomical.image.ImageService
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
    val familyService: FamilyService,

    @Autowired
    val imageService: ImageService
) {

    /**
     * Search the family, save the new atom and then insert the atom on family atoms list
     * @param familyId Represents the family unique key
     * @param atomDto Data to create the atom
     * @return Created Atom
     */
    fun create(familyId: Long, atomDto: CreateAtomDto): Atom {
        val atomFamily = familyService.getById(familyId)
        val atom = Atom(atomDto).apply {
            family = atomFamily
            atomImage = atomDto.atomImage?.run { imageService.findById(this) }
            image = atomDto.image?.run { imageService.findById(this) }
        }.run { db.save(this) }

        atomFamily.apply {
            updatedAt = LocalDateTime.now()
            atoms?.add(atom)
        }.run { familyService.save(this) }

        return atom
    }

    /**
     * Check if the family is passed, if true, get the family and "create" the relation, else, just create the atom
     * @param atomDto Data to create the atom
     * @return Created Atom
     */
    fun create(atomDto: CreateAtomDto): Atom {
        val atomFamily = atomDto.run { this.familyId?.let { familyService.getById(it) } }
        val atom = Atom(atomDto).apply {
            family = atomFamily
            atomImage = atomDto.atomImage?.run { imageService.findById(this) }
            image = atomDto.image?.run { imageService.findById(this) }
        }.run { db.save(this) }

        atomFamily?.apply {
            updatedAt = LocalDateTime.now()
            atoms?.add(atom)
        }.run { this?.let { familyService.save(it) } }

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

    /**
     * Alter atom deletedAt attribute
     * Checks if the atomId param exists, if not, throws bad request
     * @param atomId Represents the atom unique key
     */
    fun softDelete(atomId: Long): Atom {
        return getById(atomId).apply {
            deletedAt = LocalDateTime.now()
            updatedAt = LocalDateTime.now()
        }.run { db.save(this) }
    }

    /**
     * Remove atom from database
     * @param atomId Atom unique identifier
     */
    fun remove(atomId: Long) {
        return getById(atomId).run {
            db.delete(this)
        }
    }

    /**
     * Attach an atom to a family
     * @param atomId Atom unique identifier
     * @param familyId Family unique identifier
     */
    fun attachToFamily(atomId: Long, familyId: Long): Atom {
        val atom = getById(atomId)

        val atomFamily = familyService.attachAtom(atom, familyId)

        return atom.apply {
            family = atomFamily
            updatedAt = LocalDateTime.now()
        }.run { db.save(atom) }
    }

    /**
     * Update the entire atom
     */
    fun update(atomId: Long, updateAtomDto: UpdateAtomDto): Atom {
        return getById(atomId).apply {
            name = updateAtomDto.name
            number = updateAtomDto.number
            weigh = updateAtomDto.weigh
            atomImage = updateAtomDto.atomImage?.run { imageService.findById(this) } ?: this.atomImage
            image = updateAtomDto.image?.run { imageService.findById(this) } ?: this.image
            symbol = updateAtomDto.symbol
            description = (updateAtomDto.description?.run { updateAtomDto.description.toString().isEmpty() } ?: this.description).toString()
            curiosity = (updateAtomDto.curiosity?.run { updateAtomDto.curiosity.toString().isEmpty() } ?: this.curiosity).toString()
            column = updateAtomDto.column
            period = updateAtomDto.period
            updatedAt = LocalDateTime.now()
        }.run { db.save(this) }
    }

    /**
     * Update atomImage and image
     * If some of those are not send in the request, the value will be replaced with null
     */
    fun update(atomId: Long, updateAtomImagesDto: UpdateAtomImagesDto): Atom {
        return getById(atomId).apply {
            atomImage = updateAtomImagesDto.atomImage?.run { imageService.findById(this) }
            image = updateAtomImagesDto.image?.run { imageService.findById(this) }
            updatedAt = LocalDateTime.now()
        }.run { db.save(this) }
    }
}