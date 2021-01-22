package fr.dauphine.sj.monrocqxu.rdvmedecin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	
	/*@Autowired
	private UserService userService;*/
	
	private boolean isAuthenticated() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || AnonymousAuthenticationToken.class.
	      isAssignableFrom(authentication.getClass())) {
	        return false;
	    }
	    return authentication.isAuthenticated();
	}
	
	/*@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity userDetails() {
        
		List userDetails = userService.getUserDetails();
		return new ResponseEntity(userDetails, HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/espace", method = RequestMethod.GET)
    public String espace(Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = ((MyUserDetails)principal).getPrenom();
        model.addAttribute("user", username);
 
        return "espace";
    }
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
		 if (isAuthenticated()) {
		        return "redirect:espace";
		    }
        return "index";
    }
	
	
	@PostMapping("/login_success_handler")
    public String loginSuccessHandler() {
        System.out.println("Logging user login success...");
 
        return "espace";
    }
	
	@PostMapping("/login_failure_handler")
	public String loginFailureHandler() {
	    System.out.println("Login failure handler....");
	     
	    return "login";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@RequestMapping("/inscriptionMedecin")
    public String inscriptionMedecin() {
        return "inscriptionMedecin";
    }
	
	@RequestMapping("/403")
    public String erreur403() {
        return "403";
    }
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)  
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "redirect:/";  
     }  
	

}
