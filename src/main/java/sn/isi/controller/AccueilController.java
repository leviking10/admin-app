package sn.isi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/index")
public class AccueilController {
    @GetMapping()
    public String index () {
        return "accueil";
    }
    @GetMapping("/accueil")
    public String accueil () {
        return "accueil";
    }
    @GetMapping("/error")
    public String error (HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/403";
            }else if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500";
            }
        }
        return "error";
    }

    @GetMapping("/logout")
    public String logout (HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }
}
