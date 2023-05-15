package com.academy.hdemo.controller;

import com.academy.hdemo.model.Account;
import com.academy.hdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController()
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/detail")
    public Account detail(@RequestParam long accountId) {
        return accountService.findById(accountId);
    }

    @GetMapping("/detail/{id}")
    public Account detail(@PathVariable int id) {
        return accountService.findById(id);
    }

    @PostMapping("/new")
    public void newAccount(@RequestBody Account account) {
        accountService.saveAccount(account);
    }

    @PostMapping("/transfer")
    public void transferBalance(@RequestBody Map<String, Long> details) {
//    System.out.println(details.get("fromAccountNumber"));

        accountService.transferAccount(details.get("fromAccountNumber"), details.get("toAccountNumber"), details.get("amount"));
    }

    @PutMapping
    void updateAccount(@RequestBody Account account) {
        accountService.update(account);
    }

    @DeleteMapping("/delete")
    void delete(@RequestBody Account account) {
        accountService.deleteAccount(account);
    }
//    @DeleteMapping("/delete/{id}")
//    void deletetrainee(@PathVariable long id) {
//        traineeService.deletetrainee(id);
//    }

    @GetMapping("/getAll")
    public List<Account> getAllAccount() {
        return accountService.getAllAccounts();
    }



}
