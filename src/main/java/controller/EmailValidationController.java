package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class EmailValidationController {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    private static Pattern pattern;
    private Matcher matcher;

    public EmailValidationController(){
        pattern = Pattern.compile(EMAIL_REGEX);
    }

    @GetMapping("/validate")
    public ModelAndView getIndex(){
        return new ModelAndView("index");
    }

    @PostMapping("/validate")
    public ModelAndView validateEmail(@RequestParam String email, Model model){
        boolean isValid = this.validate(email);
        model.addAttribute("email",email);
        String message = "Valid Email";
        if(!isValid){
            message = "Email is invalid";
        }
        return new ModelAndView("index","message",message);
    }

    private boolean validate(String regex){
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
