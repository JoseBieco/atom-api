package api.atomical.family

import api.atomical.family.dto.CreateFamilyDto
import api.atomical.family.dto.UpdateFamilyDto
import api.atomical.family.dto.UpdateFamilyNameDto
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
@RequestMapping("family")
class FamilyController(
    @Autowired
    val service: FamilyService
) {
    /**
     * Create new entity route
     */
    @PostMapping
    fun create(@RequestBody family: CreateFamilyDto): Family {
        return service.create(family)
    }

    /**
     * Get all active families, pageable
     */
    @GetMapping
    fun getAll(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "linesPerPage", defaultValue = "5") linesPerPage: Int,
    ): Page<Family> {
        val pageRequest: PageRequest = PageRequest.of(page, linesPerPage)
        return service.getAll(pageRequest)
    }

    /**
     * Get family by id
     */
    @GetMapping("/{familyId}")
    fun getById(@PathVariable familyId: Long): Family {
        return service.getById(familyId)
    }

    /**
     * Soft delete a family route
     */
    @DeleteMapping("/{familyId}")
    fun delete(@PathVariable familyId: Long): Family {
        return service.softDelete(familyId)
    }

    /**
     * Remove a family route
     */
    @DeleteMapping("/remove/{familyId}")
    fun remove(@PathVariable familyId: Long) {
        return service.remove(familyId)
    }

    /**
     * Change the family name, route
     */
    @PatchMapping("/{familyId}/name")
    fun updateName(@PathVariable familyId: Long, @RequestBody familyDto: UpdateFamilyNameDto): Family {
        return service.changeName(familyId, familyDto)
    }

    /**
     * Update the entity Family, route
     */
    @PutMapping("/{familyId}")
    fun update(@PathVariable familyId: Long, @RequestBody familyDto: UpdateFamilyDto): Family {
        return service.update(familyId, familyDto)
    }
}