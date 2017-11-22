package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login( )
    {
        return "login";
    }

    //
    @RequestMapping("/secure")
    public String secure()
    {
        return "secure";
    }

    @RequestMapping(value="/register",method= RequestMethod.GET)
    public String showRegistrationPage(Model model)
    {
       model.addAttribute ( "user", new User () ) ;

       return "registration";
    }


    @RequestMapping("/list")
    public String listUsers(Model model)
    {
        model.addAttribute ( "users",userRepository.findAll () );

        return "list";
    }


    @RequestMapping("/detail/{id}")
    public String detailOfUser(@PathVariable("id") long id,Model model)
    {
        model.addAttribute ( "user",userRepository.findOne ( id ) );

        return "showuser";
    }

    @RequestMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id,Model model)
    {
        model.addAttribute ( "user",userRepository.findOne ( id ) );

        return "registration";
    }

    @RequestMapping("/delete/{id}")
    public String delUser(@PathVariable("id") Long id)
    {
        userRepository.delete ( id );

        return "redirect:/";
    }

    @RequestMapping(value="/register",method=RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model)
    {
        System.out.println (result );

        model.addAttribute ( "user", user );

        if(result.hasErrors ())
        {
            return "registration";
        } else{
            userService.saveUser ( user );

            model.addAttribute ( "message","User Account Succesfully Created" );
        }

        return "index";

    }





}
