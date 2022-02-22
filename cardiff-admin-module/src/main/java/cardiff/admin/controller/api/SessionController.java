package cardiff.admin.controller.api;


import cardiff.admin.dto.AdminLoginDto;
import cardiff.admin.service.AdminLoginService;
import cardiff.domain.entity.Admin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/session")
@RequiredArgsConstructor
@Slf4j
public class SessionController {

    private final EntityManager em;
    private final AdminLoginService adminLoginService;

    @PostMapping(value = "/login")
    public Object login(@RequestBody AdminLoginDto.Req req, HttpServletRequest request){

        boolean isLogin = adminLoginService.doLogin(req, request);

        return isLogin;
    }

    @DeleteMapping(value = "/logout")
    public Object logout(){

        System.out.println("=====================");
        System.out.println("delete");
        return null;
    }
}
