package cardiff.admin.service;

import cardiff.admin.dto.AdminLoginDto;
import cardiff.domain.entity.Admin;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface AdminService {
    Long join(Admin admin);

//    boolean doLogin(AdminLoginDto.Req req, HttpServletRequest httpServletRequest);

//    void doLogout(HttpServletRequest httpServletRequest);

//    PersonModel getPerson(HttpSession session);
}
