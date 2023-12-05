package com.bezkoder.spring.security.jwt.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.spring.security.jwt.models.Account;
import com.bezkoder.spring.security.jwt.repository.AccountRepository;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {
  @Autowired
  AccountRepository accountRepository;

  /**
   * Override phương thức trong class UserDetailsService
   *
   * @param username
   * @return UserDetailsImpl là implements của UserDetails (UserDetails là đối tượng Spring security sử dụng để authen và authorize)
   * @throws UsernameNotFoundException
   */
  
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account user = accountRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return AccountDetailsImpl.build(user);
  }
  
//  @Transactional
//  public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
//    Account user = accountRepository.findByEmail(email)
//        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
//
//    return AccountDetailsImpl.build(user);
//  }

}
