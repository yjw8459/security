package yjw.test.security.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String view = "/";
            HttpSession session = request.getSession();
            session.setAttribute("greeting", authentication.getName() + "님 반갑습니다.");
            String role = authentication.getAuthorities().toString();
            if ( role.contains(Roles.ROLE_USER.toString()) ) view = "/user/main";
            response.sendRedirect(view);
    }
}
