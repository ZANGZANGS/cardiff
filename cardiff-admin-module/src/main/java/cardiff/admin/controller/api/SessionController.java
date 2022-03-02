package cardiff.admin.controller.api;


import cardiff.admin.dto.AdminLoginDto;
import cardiff.admin.service.AdminService;
import cardiff.domain.entity.Admin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class SessionController {

    private final AdminService adminLoginService;

    @PostMapping(value = "/join")
    public void createAdmin(@RequestBody AdminLoginDto.Req req){
        adminLoginService.join(new Admin(req.getUsername(), req.getPassword()));
    }

}
