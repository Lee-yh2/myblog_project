package com.lyhcoding.myblog.core.auth;

import com.lyhcoding.myblog.model.user.User;
import com.lyhcoding.myblog.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userPS = userRepository.findByUsername(username).orElseThrow(
                ()->new UsernameNotFoundException("Bad Credential") // failHandler가 처리함
        );
        return new MyUserDetails(userPS);
    }
}
