package api.atomical.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ApiController {
    @GetMapping("ping")
    fun ping(): String{
        return "Pong"
    }
}