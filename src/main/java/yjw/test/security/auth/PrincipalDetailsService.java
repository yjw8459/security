package yjw.test.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yjw.test.security.repository.UserRepository;
import yjw.test.security.vo.User;

/*
시큐리티 설정에서 loginProcessingUrl("/login") 걸어놓으면
login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
 */
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    /*
    리턴된 값은
    * Security Session = Authentication = UserDetails
     1. return된 UserDetails는 Authentication의 내부 UserDetails로 들어간다.
     2. Authentication은 Security Session의 내부 Authentication으로 들어간다.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if ( userEntity != null ){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
