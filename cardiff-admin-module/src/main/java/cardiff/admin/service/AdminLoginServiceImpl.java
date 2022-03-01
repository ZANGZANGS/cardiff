package cardiff.admin.service;

import cardiff.admin.dto.AdminLoginDto;
import cardiff.admin.dto.AdminSessionDto;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminLoginServiceImpl implements AdminLoginService, UserDetailsService {

    private final AdminLoginRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long join(Admin admin) {
        admin.passwordEncoding(passwordEncoder);
        return repository.save(admin);
    }

//    @Override
//    @Transactional
//    public boolean doLogin(AdminLoginDto.Req req, HttpServletRequest httpServletRequest) {
//
//        Optional<Admin> findAdmin = repository.findOne(req.getUsername(), req.getPassword());
//
//        if(findAdmin.isPresent()){
//            Admin admin = findAdmin.get();
//
//            AdminSessionDto adminSessionDto = AdminSessionDto.builder()
//                    .id(admin.getId())
//                    .username(admin.getName())
//                    .lastVisitDateTime(admin.getLastVisitDateTime())
//                    .build();
//
//            HttpSession httpSession = httpServletRequest.getSession(true);
//            httpSession.setAttribute("ADMIN", adminSessionDto);
//
//            admin.updateLastLoginDateTime();
//
//            log.debug("관리자 로그인: {}", admin.getName());
//
//            return true;
//
//        }
//
//        return false;
//
//    }
//
//    @Override
//    public void doLogout(HttpServletRequest httpServletRequest) {
//        HttpSession session = httpServletRequest.getSession();
//
//        if(null != session){
//            session.invalidate();
//            log.debug("관리자 로그아웃");
//        }
//
//    }

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
