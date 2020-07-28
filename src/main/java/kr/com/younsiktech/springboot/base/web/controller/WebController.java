package kr.com.younsiktech.springboot.base.web.controller;

import kr.com.younsiktech.springboot.base.web.dto.WebResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
public class WebController {

    @GetMapping("/home")
    public String home(
            @RequestHeader Map<String, String> headers,
            HttpServletResponse httpServletResponse
    ) {
        log.info("Move to Home");
        return "home";
    }

    @GetMapping("/home/dto")
    public WebResponseDto webDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new WebResponseDto(name, amount);
    }
}