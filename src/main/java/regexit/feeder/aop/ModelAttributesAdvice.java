package regexit.feeder.aop;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import regexit.feeder.domain.User;

import javax.servlet.http.HttpSession;

// TODO rewrite when find out how to write this login on thymeleaf side
@ControllerAdvice
public class ModelAttributesAdvice {

    @ModelAttribute("name")
    public void name(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            model.addAttribute("name", authentication.getName());
        } else {
            model.addAttribute("name", "unknown");
        }
    }

    @ModelAttribute("user")
    public void user(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication != null) {
            model.addAttribute("user", authentication.getPrincipal());

            if(authentication.getPrincipal() instanceof User) {
                model.addAttribute("currentUserId", ((User)authentication.getPrincipal()).getId());
            }

        }
    }

    @ModelAttribute("isAdmin")
    public void isAdmin(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        model.addAttribute("isAdmin", authentication != null);
    }

//    @ModelAttribute("springSecurityLastException")
//    public void session(Model model) {
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpSession session = attr.getRequest().getSession(true);// true == allow create
//        model.addAttribute("SPRING_SECURITY_LAST_EXCEPTION", session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION"));
//    }

}
