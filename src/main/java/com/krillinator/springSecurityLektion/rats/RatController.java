package com.krillinator.springSecurityLektion.rats;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.krillinator.springSecurityLektion.Meds.Diagnos;
import com.krillinator.springSecurityLektion.Meds.DiagnosRepository;
import com.krillinator.springSecurityLektion.user.UserModel;
import com.krillinator.springSecurityLektion.user.UserModelRepository;
import com.krillinator.springSecurityLektion.user.UserModelService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RatController {

    private final RatService ratService;
    private final UserModelService userService;
    private final UserModelRepository userRepository;
    private final DiagnosRepository diagnosRepository;

    @Autowired
    public RatController(RatService ratService, UserModelService userService, UserModelRepository userRepository,
            DiagnosRepository diagnosRepository) {
        this.ratService = ratService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.diagnosRepository = diagnosRepository;
    }

    @GetMapping("/home")
    public String home(Model model, Authentication auth) {
        String username = auth.getName();
        Optional<UserModel> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            List<Rat> ratList = user.getRats();
            model.addAttribute("ratList", ratList);
            model.addAttribute("rat", new Rat());
        }
        return "home";
    }

    @PostMapping("/add-rat")
    public String addRat(@ModelAttribute Rat rat, Authentication authentication, Model model) {
        // Hämta inloggade användaren
        UserModel user = userService.getUserFromAuthentication(authentication);

        // Lägg till råtta till användarens lista
        user.addRat(rat);
        userService.saveUser(user);

        // Lägg till meddelande i modellen att råttan lades till
        model.addAttribute("successMessage", "Rat added successfully!");

        // Återvänd till samma vy (home.html) för att visa meddelandet
        return "redirect:/home";
    }

    @GetMapping("/add-rat")
    public String showAddRatForm(Model model) {
        model.addAttribute("rat", new Rat());
        return "home :: add-rat-section";
    }

    @GetMapping("/add-diagnos")
    public String showAddDiagnosForm(Model model, @RequestParam("ratId") Long ratId) {
        System.out.println("början av showAddDiagnosForm");
        Rat rat = ratService.getRatById(ratId);
        System.out.println("hämtat råtta via ratId: " + rat);

        if (rat != null) {
            Diagnos diagnos = new Diagnos();
            diagnos.setRat(rat);
            diagnos.setRatsId(rat.getId());
            System.out.println("sparat råttan till diagnosen: " + diagnos);

            model.addAttribute("diagnos", diagnos);
        }
        return "add-diagnos";
    }

   
      @PostMapping("/add-diagnos")
      public String addDiagnos(@ModelAttribute("diagnos") Diagnos
      diagnos, @RequestParam("ratId") Long ratId) {
      System.out.println("början av postmappingen addDiagnos");
      
      Rat rat = ratService.getRatById(ratId);
      if (rat != null) {
      diagnos.setRat(rat);
      rat.getDiagnoser().add(diagnos);
      diagnosRepository.save(diagnos);
      }
      return "redirect:/home";
      }
     



}
