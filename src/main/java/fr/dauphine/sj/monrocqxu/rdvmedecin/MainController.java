package fr.dauphine.sj.monrocqxu.rdvmedecin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity userDetails() {
        
		List userDetails = userService.getUserDetails();
		return new ResponseEntity(userDetails, HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = ((MyUserDetails)principal).getPrenom();
        model.addAttribute("user",username);
 
        return "index";
    }
	
	@PostMapping("/login_success_handler")
    public String loginSuccessHandler() {
        System.out.println("Logging user login success...");
 
        return "index";
    }
	
	@PostMapping("/login_failure_handler")
	public String loginFailureHandler() {
	    System.out.println("Login failure handler....");
	     
	    return "login";
	}

}
