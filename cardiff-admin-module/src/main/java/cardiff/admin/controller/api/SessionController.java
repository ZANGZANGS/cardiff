package cardiff.admin.controller.api;


import cardiff.admin.dto.AdminLoginDto;
import cardiff.admin.service.AdminLoginService;
import cardiff.domain.entity.Admin;
import cardiff.domain.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class SessionController {

    private final AdminLoginService adminLoginService;

    @PostMapping(value = "/join")
    public void createAdmin(@RequestBody AdminLoginDto.Req req){
        adminLoginService.join(new Admin(req.getUsername(), req.getPassword()));
    }

//    @PostMapping(value = "/login")
//    public ResponseEntity login(@RequestBody AdminLoginDto.Req req, HttpServletRequest request){
//
//        if(!adminLoginService.doLogin(req,request)){
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ErrorResponse("일치하는 관리자 정보가 없습니다."));
//        }
//        return ResponseEntity.ok(new CommonResponse<>());
//    }

//    @DeleteMapping(value = "/logout")
//    public ResponseEntity logout(HttpServletRequest request){
//        adminLoginService.doLogout(request);
//        return ResponseEntity.ok(new CommonResponse<>());
//    }
}
