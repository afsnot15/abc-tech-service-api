package br.com.fiap.abctechservice.security;

import br.com.fiap.abctechservice.model.Operator;
import br.com.fiap.abctechservice.repository.OperatorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtUserDetailService implements UserDetailsService {

    private final OperatorRepository operatorRepository;

    public JwtUserDetailService(OperatorRepository userRepository) {
        this.operatorRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Operator operator = operatorRepository.findFirstByRegistration(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                operator.getName(),
                operator.getPassword(),
                new ArrayList<>() // Roles
        );
    }
}