package com.tripmanager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping("")
	public String login() {
		return "redirect:/dashboard";
	}
}
