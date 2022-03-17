package api.atomical.api

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ApiController {
    @GetMapping("ping")
    fun ping(): ResponseEntity<String> {
        return ResponseEntity.ok("Pong")
    }
}