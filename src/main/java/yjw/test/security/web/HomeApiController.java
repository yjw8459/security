package yjw.test.security.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import yjw.test.security.repository.UserRepository;
import yjw.test.security.vo.User;

@RestController
@RequiredArgsConstructor
public class HomeApiController {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public String manager(){
        return "manager";
    }

    /**
     * Spring Security가 매핑 낚아챔 localhost:8080/login
     * SecurityConfig 생성 시 낚아채지 않음
     * @return
     */
//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join")
    public String join(User user){
        user.setRole("ROLE_USER");
        String encPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encPassword);
        userRepository.save(user);
        return "redirect:/loginForm";
    }
}
