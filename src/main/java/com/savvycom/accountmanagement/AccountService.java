package com.savvycom.accountmanagement;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public List<Account> getAll(){
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    public Account getById(Long id){
        Optional<Account> account = accountRepository.findById(id);
        return account.orElse(null);
    }

    public Account create(Optional<String> username, Optional<String> password, String fullName, Integer age, String gender, String address, Optional<String> role){
        Account account = new Account();
        account.setUsername(username.orElseThrow(() -> new IllegalArgumentException("Username ko dung dinh dang")));
        account.setAddress(address);
        account.setPassword(passwordEncoder.encode(password.orElseThrow(() -> new IllegalArgumentException("Password ko dung dinh dang"))));
        account.setAge(age);
        account.setGender(gender);
        account.setFullName(fullName);
        account.setRole(role.orElseThrow(() -> new IllegalArgumentException("Role ko dung dinh dang")));
        accountRepository.save(account);
        return account;
    };

    public Account update(Long id, Optional<String> username, Optional<String> password, String fullName, Integer age, String gender, String address, Optional<String> role){
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ko ton tai account nay!"));
        account.setUsername(username.orElseThrow(() -> new IllegalArgumentException("Username ko dung dinh dang!")));
        account.setAddress(address);
        account.setAge(age);
        account.setPassword(passwordEncoder.encode(password.orElseThrow(() -> new IllegalArgumentException("Password ko dung dinh dang"))));
        account.setGender(gender);
        account.setFullName(fullName);
        account.setRole(role.orElseThrow(() -> new IllegalArgumentException("Role ko dung dinh dang")));
        accountRepository.save(account);
        return account;
    }

    public Account delete(Long id){
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ko ton tai account nay!"));
        accountRepository.delete(account);
        return account;
    }

}
