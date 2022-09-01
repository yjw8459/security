package yjw.test.security.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class HomeController {

    @GetMapping({"", "/"})
    public String index(){
        return "index";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        String page = "loginForm";

        return page;
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    @GetMapping("/success")
    public ModelAndView success(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info("Success usernama: {}", userDetails.getUsername());
        return new ModelAndView("success").addObject("user", userDetails);
    }
}
