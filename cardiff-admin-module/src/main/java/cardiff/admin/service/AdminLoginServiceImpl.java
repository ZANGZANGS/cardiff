package cardiff.admin.service;

import cardiff.domain.entity.Admin;
import cardiff.domain.repository.AdminLoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminLoginServiceImpl implements AdminService, UserDetailsService {

    private final AdminLoginRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long join(Admin admin) {
        admin.passwordEncoding(passwordEncoder);
        return repository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Admin> findAdmin = repository.findByName(username);

        if(findAdmin.isEmpty()){
            throw new UsernameNotFoundException("일치하는 관리자 정보가 없습니다.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));

        UserDetails admin = new User(findAdmin.get().getName(), findAdmin.get().getPassword(), authorities);

        log.debug("spring-security 관리자 로그인: {}", findAdmin.get().getName());

        return admin;

    }
}
