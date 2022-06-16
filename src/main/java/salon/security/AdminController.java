package salon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
@Autowired
    private ClientService clientService;

        @GetMapping("/admin")
    public String userList(Model model){
    model.addAttribute("allClients", clientService.allClients());
    return "/admin";
        }

        @PostMapping("/admin")
    public String deleteClient(@RequestParam(required = true, defaultValue = "") Long clientId,
                               @RequestParam(required = true, defaultValue = "") String action,
                               Model model){
            if(action.equals("delete")){
                clientService.deleteClient(clientId);
            }
            return "redirect:/admin";
        }

       /* @GetMapping("/admin/gt/{clientId}")
    public String gtClient(@PathVariable("clientId") Long clientId, Model model){
            model.addAttribute("oneClient", clientService.findClientById(clientId));
            return "admin";
        }*/
}
