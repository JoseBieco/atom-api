package api.atomical.atom

import api.atomical.atom.dto.CreateAtomDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
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
     * Create atom and relate it to family route
     */
    @PostMapping("/family/{familyId}")
    fun create(@PathVariable familyId: Long, @RequestBody atomDto: CreateAtomDto): Atom {
        return service.create(familyId, atomDto)
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
}