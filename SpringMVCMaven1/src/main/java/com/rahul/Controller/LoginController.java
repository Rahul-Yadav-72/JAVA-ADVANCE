package com.rahul.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String loginpage() {
		return "login";
	}
	// Handle login page code 
	@PostMapping("/doLogin")
	public String doLogin(@RequestParam("username" ) String username,@RequestParam("password") String password, HttpSession session, Model model) {
		if(username.equals("rahul") && password.equals("123456")) {
			session.setAttribute("user", username);
			return "redirect:/dashboard";
		} else {
			model.addAttribute("error", "invalid username or password");
			return "login";
		}
	}
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session) {
		if(session.getAttribute("user")==null) {
			return "redirect:/login";
		}
		return "dashboard";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login"; //loging.jsp
	}

}
