package com.savvycom.accountmanagement;

import com.savvycom.accountmanagement.Account;
import com.savvycom.accountmanagement.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Auhtor: Nông Văn Hoạt
 * @since 22/06/2022
 */
@Service("UserForAuthorization")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Lấy user từ database
     * @param userName the username identifying the user whose data is required.
     *
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<Account> user = this.accountRepository.findAccountByUsername(userName);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User này chưa được đăng ký");
        }
        return UserDetailsImpl.build(user.get());

    }

}
