package regexit.feeder.aop;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

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
        if(authentication != null) {
            model.addAttribute("user", authentication.getPrincipal());
        }
    }

    @ModelAttribute("isAdmin")
    public void isAdmin(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        model.addAttribute("isAdmin", authentication != null);
    }
}
