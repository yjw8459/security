package yjw.test.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yjw.test.security.repository.UserRepository;
import yjw.test.security.vo.Users;

import java.util.ArrayList;
import java.util.List;

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
        Users usersEntity = userRepository.findByUsername(username);
        if ( usersEntity != null ){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(usersEntity.getRole()));
            //return new PrincipalDetails(usersEntity);
            return new User(usersEntity.getUsername(), usersEntity.getPassword(),authorities);
        }
        return null;
    }
}
