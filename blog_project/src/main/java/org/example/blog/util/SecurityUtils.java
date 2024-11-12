package org.example.blog.util;

import org.example.blog.entity.common_interface.HasUser;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class SecurityUtils {
    private static final String ACCESS_DENIED = "Access Denied";
    private static final String ADMIN = "ADMIN";

    public static Authentication getAuthentication(){
        Object principal = SecurityContextHolder
                .getContext().getAuthentication();

        if (principal instanceof Authentication authentication){
            return authentication;
        }else {
            throw new AccessDeniedException(ACCESS_DENIED);
        }
    }

    public static void checkAuthor(HasUser user){
        if (!isAuthor(user)){
            throw new AccessDeniedException(ACCESS_DENIED);
        }
    }

    public static void checkIsAuthorOrAdmin(HasUser user){
        if (!isAuthor(user) && !isAdmin()){
            throw new AccessDeniedException(ACCESS_DENIED);
        }
    }

    public static boolean isAuthor(HasUser user){
        return isAuthor(getAuthentication(), user);
    }

    private static boolean isAuthor(Authentication authentication, HasUser user){
        return Objects.equals(authentication.getName(), user.getUserId());
    }

    public static boolean isAdmin(){
        return isAdmin(getAuthentication());
    }

    private static boolean isAdmin(Authentication authentication){
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(o -> o.equals("ROLE_" + ADMIN));
    }
}
