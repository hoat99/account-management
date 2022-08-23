package com.savvycom.accountmanagement.controller;

import com.savvycom.accountmanagement.Account;
import com.savvycom.accountmanagement.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final AccountService accountService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/user")
    public String user(){
        return "user";
    }

    @GetMapping
    public List<Account> getAll(){
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable(name = "id") Long id){
        return accountService.getById(id);
    }

    @PostMapping
    public Account create(@RequestParam(name = "username") Optional<String> username,
                          @RequestParam(name = "password") Optional<String> password,
                          @RequestParam(name = "fullName") String fullName,
                          @RequestParam(name = "age") Integer age,
                          @RequestParam(name = "gender") String gender,
                          @RequestParam(name = "address") String address,
                          @RequestParam(name = "role") Optional<String> role){
        return accountService.create(username,password,fullName,age,gender,address,role);
    }

    @PutMapping("/{id}")
    public Account update(@PathVariable(name = "id") Long id,
                          @RequestParam(name = "username") Optional<String> username,
                          @RequestParam(name = "password") Optional<String> password,
                          @RequestParam(name = "fullName") String fullName,
                          @RequestParam(name = "age") Integer age,
                          @RequestParam(name = "gender") String gender,
                          @RequestParam(name = "address") String address,
                          @RequestParam(name = "role") Optional<String> role){
        return accountService.update(id,username,password,fullName,age,gender,address,role);
    }

    @DeleteMapping("/{id}")
    public Account delete(@PathVariable(name = "id") Long id){
        return accountService.delete(id);
    }



}
