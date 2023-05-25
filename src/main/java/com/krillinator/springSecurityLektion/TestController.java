package com.krillinator.springSecurityLektion;

import com.krillinator.springSecurityLektion.configurations.AppPasswordConfig;
import com.krillinator.springSecurityLektion.user.UserModel;
import com.krillinator.springSecurityLektion.user.UserModelRepository;
import com.krillinator.springSecurityLektion.user.authorities.UserRoles;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/** For debugging ONLY */

@Controller
public class TestController {

    private final UserModelRepository userModelRepository;
    private final AppPasswordConfig appPasswordConfig;
    

    @Autowired
    public TestController(UserModelRepository userModelRepository, AppPasswordConfig appPasswordConfig) {
        this.userModelRepository = userModelRepository;
        this.appPasswordConfig = appPasswordConfig;
    }

    @GetMapping("/register")
    public String displayRegisterUser(UserModel userModel) { 

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserModel userModel, BindingResult result, Model model) {

        if (result.hasErrors()) {

            return "register";
        }

        String role = String.valueOf(userModel.getAuthorities().iterator().next());

        switch (role) {
            case "Admin" -> userModel.setAuthorities(UserRoles.ADMIN.getGrantedAuthorities());
            case "User" -> userModel.setAuthorities(UserRoles.USER.getGrantedAuthorities());
        }

        userModel.setPassword(appPasswordConfig.bCryptPasswordEncoder().encode(userModel.getPassword()));
        userModel.setAccountNonExpired(true);
        userModel.setAccountNonLocked(true);
        userModel.setCredentialsNonExpired(true);
        userModel.setEnabled(true);

        // IF no errors
        System.out.println(userModel);
        userModelRepository.save(userModel);
        /* testar detta för ett konstigt error... */
        model.addAttribute("userModel", new UserModel());

        return "redirect:/home";
    }


    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/error-page")
    public String errorPage(){
        return "error-page";
    }
    

}
