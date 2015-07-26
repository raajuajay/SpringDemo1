package com.jpt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jpt.model.User;
import com.jpt.model.UserLogin;
import com.jpt.service.UserService;


@Controller
public class DemoController {
	
	@Autowired   //@Autowired annotation allows you to skip configurations elsewhere of what to inject and just does it for you
	UserService userService;   //function looks similar to @Inject::this is kind of an injection anyway
	
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String getUser(ModelMap map){
		User user = new User();
		map.addAttribute("userBean", user);
		return "user";
	}
	
	/*
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public ModelAndView getAddedUser(@ModelAttribute("userBean") User user){
		ModelAndView modelandview = new ModelAndView();
		modelandview.addObject("id", user.getId());
		modelandview.addObject("firstName", user.getFirstName());
		modelandview.addObject("lastName", user.getLastName());
		modelandview.addObject("address", user.getAddress());
		modelandview.addObject("email", user.getEmail());
		modelandview.addObject("age", user.getAge());
		modelandview.setViewName("addUser");
		return modelandview;
	}
	*/
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public String getAddedUser(@ModelAttribute("userBean") User user){
		userService.createUser(user);
		return "success";
		
	}
	
	
	@RequestMapping(value="/getUser/{email}", method=RequestMethod.GET)
	@ResponseBody
	public String getUser(@PathVariable("email") String em){
		User user = userService.getUserByEmail(em);
		return user.toString();
		
	}
	
	
	
	@RequestMapping(value="/getUserById/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String getUserById(@PathVariable("id") String id){
		User user = userService.getUserById(Integer.parseInt(id));
		return user.toString();
		
	}
	
	
	
	@RequestMapping(value="/addOneUser",method=RequestMethod.POST)
	public String addUser(@ModelAttribute("userBean") User user){
				return "success";
	}
	
	
	@RequestMapping(value="/updateUser",method=RequestMethod.GET)
	public String updateUser(@RequestParam("id") String id){
		User user=userService.getUserById(Integer.parseInt(id));
		user.setFirstName("Updated");
		userService.updateUser(user);
		return "success";
	}
	
	
	@RequestMapping(value="/deleteUser", method=RequestMethod.GET)
	public String deleteU(@RequestParam("id") String id){
		//User user = userService.getUserById(Integer.parseInt(id));
		userService.deleteU(Integer.parseInt(id));
		return "success";
	}
	
	
	@RequestMapping(value="/allUsers", method=RequestMethod.GET)
	public ModelAndView getAllUsers(){
		List<User> userlist = userService.getAllUser();
		ModelAndView mv= new ModelAndView("userList","users",userlist);
		return mv;
		
	}
	
	@RequestMapping(value="/userLogin/{uname}/{psd}", method = RequestMethod.GET)
	@ResponseBody
	public String getUserLogin(@PathVariable("uname")String userName,@PathVariable("psd")String password){
		
	UserLogin userlog = userService.loginUser(userName, password);
	
	return userlog.toString();
}
	
	
	
	@RequestMapping(value="/getAllUserOrders/{firstName}/{lastName}", method = RequestMethod.GET)
	@ResponseBody
	public String getAllUserOrders(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
		List<User> userList = userService.getAllOrder(firstName, lastName);
		String result="";
		for(User u: userList){
			result = result + u.toString();
		}
		return result;
	}
	
	
}
