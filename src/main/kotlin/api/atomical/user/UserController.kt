package api.atomical.user

import api.atomical.user.dto.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(
    @Autowired
    val service: UserService
) {
    /**
     * Get by id
     * Get All -> Active
     * Update
     * Soft Delete
     * Destroy -> remove user from database
     */

    /**
     * Get all active users, pageable
     */
    @GetMapping
    fun getAll(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "linesPerPage", defaultValue = "5") linesPerPage: Int,
    ): Page<User> {
        val pageRequest: PageRequest = PageRequest.of(page, linesPerPage)
        return service.getAll(pageRequest)
    }

    /**
     * Soft delete user
     */
    @DeleteMapping("/{userId}")
    fun delete(@PathVariable userId: Long): User {
        return service.softDelete(userId)
    }

    /**
     * Update user's name and/or email
     */
    @PutMapping("/{userId}")
    fun update(@PathVariable userId: Long, @RequestBody body: UserDto): User {
        return service.update(userId, body)
    }

    /**
     * Get User entity  by id
     */
    @GetMapping("/{userId}")
    fun getById(@PathVariable userId: Long): User {
        return service.getById(userId)
    }
}