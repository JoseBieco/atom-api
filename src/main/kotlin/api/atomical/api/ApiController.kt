package api.atomical.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ApiController {
    @RequestMapping("ping")
    fun ping(): String{
        return "Pong"
    }
}