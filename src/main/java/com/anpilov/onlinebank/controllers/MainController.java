package com.anpilov.onlinebank.controllers;

import com.anpilov.onlinebank.models.Transaction;
import com.anpilov.onlinebank.services.TransactionService;
import com.anpilov.onlinebank.user.User;
import com.anpilov.onlinebank.user.UserRepository;
import com.anpilov.onlinebank.user.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 This is the main controller class that handles all requests and responses.
 @author Anpilov Kirill

 @version 1.0
 */
@Controller
public class MainController {


    @Autowired
    private TransactionService service;

    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;


    /**
     Method to get the user details from the security context.
     @return User object representing the current user.
     */
    public User getUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        System.out.println(email);
        Matcher matcher = Pattern.compile("(Username=)([^,]+)").matcher(email);

        if (matcher.find()) {
            email = matcher.group(2);
            System.out.println(email);

        }
        User user = userRepository.findByEmail(email);
        return user;
    }

    /**

     Method to handle requests for the home page.

     @param model the model object used to pass data to the view.

     @param keyword the search keyword if any.

     @return the name of the view to display.
     */
    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword){
        User user = getUser();
        Long user_id = user.getId();
        List<Transaction> listTran = service.listAllTranByUser(user_id, keyword);
        model.addAttribute("User", user);
        model.addAttribute("listTran", listTran);

        HashMap<Long, String> alluser = new HashMap<>();
        for (Transaction tran: listTran){
            alluser.put(tran.getId_payee(), userRepository.findById(tran.getId_payee()).get().getFirstName());
            alluser.put(tran.getId_payer(), userRepository.findById(tran.getId_payer()).get().getFirstName());

        }
        model.addAttribute("alluser", alluser);
        return "index";
    }

    /**
    Method to handle requests for the admin page.
    @param model the model object used to pass data to the view.
    @return the name of the view to display.
    **/
    @RequestMapping("/admin")
    public String viewAdminPage(Model model){
        return "admin";
    }



    /**

     Method to handle requests for the author page.
     @param model the model object used to pass data to the view.
     @return the name of the view to display.
     */

    @RequestMapping("/author")
    public String viewAuthorPage(Model model){
        return "author";
    }


    /**

     Method to handle requests for the analytics page.
     @param model the model object used to pass data to the view.
     @param keyword the search keyword if any.
     @return the name of the view to display.
     */
    @RequestMapping("/analyze")
    public String viewAnalyticsPage(Model model, @Param("keyword") String keyword){
        User user = getUser();
        Long user_id = user.getId();
        List<Transaction> listTran = service.listAllTranByUser(user_id, keyword);
        model.addAttribute("User", user);
        model.addAttribute("listTran", listTran);
        return "analyze";
    }


    /**

     Deletes a session with a specified ID.
     @param id the ID of the session to be deleted
     @return a string that redirects to the analyze page
     */
    @RequestMapping("/delete/{id}")
    public String deleteSession(@PathVariable(name = "id") Long id){
        service.delete(id);
        return "redirect:/analyze";

    }



    /**

     Shows a form for creating a new transfer.

     @param model the model to be used for the form

     @return a string representing the view for the new transfer form
     */
    @GetMapping("/transfer")
    public String showNewSessionForm(Model model){
        Transaction transaction = new Transaction();
        model.addAttribute("Transaction", transaction);
        model.addAttribute("balance", getUser().getBalance());

        return "new_transfer";
    }


    /**

     Saves a new transaction.

     @param transaction the transaction to be saved

     @param result the result of the transaction

     @return a string that redirects to the home page

     @throws InterruptedException if the thread is interrupted while sleeping
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveSession(@ModelAttribute("Transaction") Transaction transaction, BindingResult result) throws InterruptedException {

        User currentUser = getUser();
        Long currentuUserId = currentUser.getId();
        transaction.setId_payer(currentuUserId);


        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        transaction.setData(currentDate.format(formatter));
        System.out.println(transaction.toString());


        if (currentUser.getBalance() < transaction.getMoney()) {
            result.rejectValue("money", "error.money", "Недостаточно средств на счете");
        }

        if (userRepository.findById(transaction.getId_payee()).isEmpty() || Objects.equals(currentuUserId, transaction.getId_payee()))  {
            result.rejectValue("id_payee", "error.id_payee", "Пользователь не найден или это вы же");
        }
        if (result.hasErrors()) {
            return "new_transfer";
        }
        userService.updateBalanceByUser_id(currentuUserId, currentUser.getBalance() - transaction.getMoney());
        userService.updateBalanceByUser_id(transaction.getId_payee(), userRepository.findById(transaction.getId_payee()).get().getBalance() + transaction.getMoney());
        service.save(transaction);





        return "redirect:/";
    }




//    @RequestMapping("/edit/{id}")
//    public ModelAndView showEditSessionFrom(@PathVariable(name = "id") Long id){
//        ModelAndView mav = new ModelAndView("edit_transaction");
//        Transaction transaction = service.get(id);
//        mav.addObject("Transaction", transaction);
//        return mav;
//    }
//    @RequestMapping("/delete/{id}")
//    public String deleteSession(@PathVariable(name = "id") Long id){
//        service.delete(id);
//        return "redirect:/";
//
//    }


}


