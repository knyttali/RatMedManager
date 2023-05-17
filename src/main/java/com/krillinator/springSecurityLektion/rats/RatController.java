package com.krillinator.springSecurityLektion.rats;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.krillinator.springSecurityLektion.Meds.Medication;
import com.krillinator.springSecurityLektion.Meds.TreatmentForm;
import com.krillinator.springSecurityLektion.user.UserModel;
import com.krillinator.springSecurityLektion.user.UserModelRepository;
import com.krillinator.springSecurityLektion.user.UserModelService;

@Controller
public class RatController {

    private final RatService ratService;
    private final UserModelService userService;
    private final UserModelRepository userRepository;

    @Autowired
    public RatController(RatService ratService, UserModelService userService, UserModelRepository userRepository) {
        this.ratService = ratService;
        this.userService = userService;
        this.userRepository = userRepository;
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

    

    // Visa formuläret för att lägga till en behandling för en råtta
    @GetMapping("/rats/{id}/add-treatment")
    public String showAddTreatmentForm(@PathVariable Long id, Model model) {
        Rat rat = ratService.getRatById(id);
        TreatmentForm treatmentForm = new TreatmentForm();
        treatmentForm.setRatId(rat.getId());
        model.addAttribute("treatmentForm", treatmentForm);
        model.addAttribute("rat", rat);
        return "treatment-form";
    }

    @PostMapping("/rats/{id}/add-treatment")
    @ResponseBody
    public ResponseEntity<String> addTreatment(@PathVariable Long id, @ModelAttribute TreatmentForm form) {
        Rat rat = ratService.getRatById(id);
        Medication medication = new Medication(form.getMedication(), form.getMedication());
        medication.setRat(rat);
        rat.getMedications().add(medication);
        ratService.saveRat(rat);
        return ResponseEntity.ok().body("Medication added successfully");
    }

    /* visa råttans treatments */
    @GetMapping("/rats/{id}/treatments")
    public String showTreatments(@PathVariable Long id, Model model) {
        Rat rat = ratService.getRatById(id);
        model.addAttribute("rat", rat);
        return "treatments";
    }

}
