package yjw.test.security.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import yjw.test.security.vo.Users;

import java.util.ArrayList;
import java.util.Collection;

/*
    시큐리티가 /login 낚아채서 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
    로그인을 진행이 완료되면 시큐리티 session을 만들어준다.(시큐리티 자신만의 session : SecurityContextHolder)
    오브젝트 => Authentication 타입 객체만

    Authentication 안에 User 정보가 있어야되는데
    User 오브젝트 타입 => UserDetails 타입 객체

    Security Session => Authentication => UserDetails


 */
public class PrincipalDetails implements UserDetails { //UserDetails를 구현함으로써 Authentication 객체에 넣을 수 있음

    private Users users;

    public PrincipalDetails(Users users){
        this.users = users;
    }

    //해당 User의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //user.getRole(); String 타입이기 때문에 리턴할 수 없음.
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return users.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /*
        사이트에서 1년동안 로그인을 안하였을 경우 휴면 계정으로 바꿀 경우
        user의 최종 로그인 시간과 현재시간을 비교하여 1년이 지난 경우는 false로 리턴
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
