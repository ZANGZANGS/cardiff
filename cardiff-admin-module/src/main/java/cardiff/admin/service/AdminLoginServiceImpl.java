package cardiff.admin.service;

import cardiff.admin.dto.AdminLoginDto;
import cardiff.admin.dto.AdminSessionDto;
import cardiff.domain.repository.AdminLoginRepository;
import cardiff.domain.entity.Admin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminLoginServiceImpl implements AdminLoginService {

    private final AdminLoginRepository repository;


    @Override
    @Transactional(readOnly = false)
    public Long join(Admin admin) {
        return repository.save(admin);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean doLogin(AdminLoginDto.Req req, HttpServletRequest httpServletRequest) {

        Optional<Admin> findAdmin = repository.findOne(req.getName(), req.getPassword());

        if(findAdmin.isPresent()){
            Admin admin = findAdmin.get();

            AdminSessionDto adminSessionDto = AdminSessionDto.builder()
                    .id(admin.getId())
                    .name(admin.getName())
                    .lastVisitDateTime(admin.getLastVisitDateTime())
                    .build();

            HttpSession httpSession = httpServletRequest.getSession(true);
            httpSession.setAttribute("ADMIN", adminSessionDto);

            admin.updateLastLoginDateTime();

            log.debug("관리자 로그인: {}", admin.getName());

            return true;

        }

        return false;

    }

    @Override
    public void doLogout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();

        if(null != session){
            session.invalidate();
            log.debug("관리자 로그아웃");
        }

    }

}
