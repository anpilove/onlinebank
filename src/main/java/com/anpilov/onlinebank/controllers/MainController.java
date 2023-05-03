package com.anpilov.onlinebank.controllers;

import com.anpilov.onlinebank.models.Transaction;
import com.anpilov.onlinebank.services.TransactionService;
import com.anpilov.onlinebank.user.User;
import com.anpilov.onlinebank.user.UserRepository;
import com.anpilov.onlinebank.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class MainController {


    @Autowired
    private TransactionService service;

    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;



    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword){
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        System.out.println(email);
        Matcher matcher = Pattern.compile("(Username=)([^,]+)").matcher(email);

        if (matcher.find()) {
            email = matcher.group(2);
            System.out.println(email);

        }
        Long user_id = userRepository.findByEmail(email).getId();
        User user = userRepository.findByEmail(email);
        List<Transaction> listTran = service.listAllTranByUser(user_id, keyword);
        List<User> listUser = userService.getAll();
        model.addAttribute("User", user);
        model.addAttribute("listTran", listTran);
        model.addAttribute("listUser", listUser);
        System.out.println(listUser);
        return "index";
    }



    @RequestMapping("/admin")
    public String viewAdminPage(Model model){
        return "admin";
    }

    @GetMapping("/transfer")
    public String showNewSessionForm(Model model){
        Transaction transaction = new Transaction();
        model.addAttribute("Transaction", transaction);
        return "new_transfer";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveSession(@ModelAttribute("Transaction") Transaction transaction){
        service.save(transaction);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditSessionFrom(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("edit_transaction");
        Transaction transaction = service.get(id);
        mav.addObject("Transaction", transaction);
        return mav;
    }
    @RequestMapping("/delete/{id}")
    public String deleteSession(@PathVariable(name = "id") Long id){
        service.delete(id);
        return "redirect:/";

    }


}