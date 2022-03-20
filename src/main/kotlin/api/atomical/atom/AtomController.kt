package api.atomical.atom

import api.atomical.atom.dto.CreateAtomDto
import api.atomical.atom.dto.UpdateAtomDto
import api.atomical.atom.dto.UpdateAtomImagesDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("atoms")
class AtomController(
    @Autowired
    val service: AtomService
) {

    /**
     * TODO
     * Refactor create route to permit send family as null and create an "attach" route
     */

    /**
     * Create atom and relate it to family route
     */
    @PostMapping("/family/{familyId}")
    fun createFromFamily(@PathVariable familyId: Long, @RequestBody atomDto: CreateAtomDto): Atom {
        return service.create(familyId, atomDto)
    }

    /**
     * Create atom route
     */
    @PostMapping
    fun simpleCreate(@RequestBody atomDto: CreateAtomDto): Atom {
        return service.create(atomDto)
    }

    @GetMapping
    fun getAll(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "linesPerPage", defaultValue = "5") linesPerPage: Int,
    ): Page<Atom> {
        val pageRequest: PageRequest = PageRequest.of(page, linesPerPage)
        return service.getAll(pageRequest)
    }

    @GetMapping("/{atomId}")
    fun getById(@PathVariable atomId: Long): Atom {
        return service.getById(atomId)
    }

    /**
     * Soft delete atom entity route
     */
    @DeleteMapping("/{atomId}")
    fun softDelete(@PathVariable atomId: Long): Atom {
        return service.softDelete(atomId)
    }

    /**
     * Remove atom from database route
     */
    @DeleteMapping("/{atomId}/remove")
    fun remove(@PathVariable atomId: Long) {
        return service.remove(atomId)
    }

    /**
     * Attach atom to family route
     */
    @PatchMapping("/{atomId}/family/{familyId}")
    fun attachAtomToFamily(@PathVariable atomId: Long, @PathVariable familyId: Long): Atom {
        return service.attachToFamily(atomId, familyId)
    }

    /**
     * Update entire atom route
     */
    @PutMapping("/{atomId}")
    fun update(@PathVariable atomId: Long, @RequestBody updateAtomDto: UpdateAtomDto): Atom {
        return service.update(atomId, updateAtomDto)
    }

    /**
     * Update Image or Atom Image
     */
    @PatchMapping("/{atomId}/images")
    fun patchImages(@PathVariable atomId: Long, @RequestBody updateAtomImagesDto: UpdateAtomImagesDto): Atom {
        return service.update(atomId, updateAtomImagesDto)
    }
}