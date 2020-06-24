package kr.com.younsiktech.springboot.base.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/")
public class WebController extends BaseViewController{

    @GetMapping("")
    public String redirect() {
        return "redirect:/home";
    }

    @GetMapping("home")
    public ModelAndView home(
            @RequestHeader Map<String, String> headers,
            HttpServletResponse httpServletResponse
    ) {
        ModelAndView modelAndView = new ModelAndView("pages/home");

        return modelAndView;
    }
}
