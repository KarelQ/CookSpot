package cookspot.com.cookspot.controller;


import cookspot.com.cookspot.service.AdminService;
import cookspot.com.cookspot.service.JwtService;
import cookspot.com.cookspot.service.PostService;
import cookspot.com.cookspot.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/admin")
public class AdminController {
    private final AdminService adminService;
    private final JwtService jwtService;
    private final UserInfoService userInfoService;

    @Autowired
    public AdminController(AdminService adminService, JwtService jwtService, UserInfoService userInfoService) {
        this.adminService = adminService;
        this.jwtService = jwtService;
        this.userInfoService = userInfoService;
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkAdmin(@RequestHeader("Authorization") String authorizationHeader ){
        String idUser = jwtService.extractRoleFromHeader(authorizationHeader);
        if(idUser.equals("ROLE_ADMIN")){
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }
}
