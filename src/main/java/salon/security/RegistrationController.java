package salon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import salon.dao.Client;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    ClientService clientService;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("clientForm", new Client());

        return "registration";
    }

    @PostMapping("/registration")
    public String addClient(@ModelAttribute("clientForm") @Valid Client clientForm, BindingResult bindingResult, Model model){
        System.out.println(clientForm.toString());
        if (bindingResult.hasErrors()){
            return "registration";
        }

        if (!clientForm.getPassword().equals(clientForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }

        if (!clientService.saveClient(clientForm)){
            model.addAttribute("clientNameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/";
    }
}
