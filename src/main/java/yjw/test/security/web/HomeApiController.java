package yjw.test.security.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import yjw.test.security.repository.UserRepository;
import yjw.test.security.vo.Users;

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
    public String join(Users users){
        users.setRole("ROLE_USER");
        String encPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(encPassword);
        userRepository.save(users);
        return "redirect:/loginForm";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public String info(){
        return "개인정보";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") //data 메서드가 실행되기 직전에 실행된다.
    @GetMapping("/data")
    public String data(){
        return "데이터정보";
    }

    @PostAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") //data 메서드가 실행 후에 실행된다.
    @GetMapping("/test")
    public String test(){
        return "데이터정보";
    }
}
