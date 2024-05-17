package be.intecbrussel.jpaonetomanydemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("javax.servlet.error.status_code");
        String errorMessage = "Unexpected error";

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == 500 || statusCode==404 || statusCode ==400 || statusCode==403|| statusCode== 401 ) {
                Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
                if (throwable != null) {
                    errorMessage = throwable.getMessage();
                }
            }
        }

        model.addAttribute("errorMessage", errorMessage);
        return "error";  // Name of the error HTML template
    }

}