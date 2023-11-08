package pe.com.backend.kenny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.backend.kenny.model.request.Login;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.model.response.LoginResponse;
import pe.com.backend.kenny.service.ILoginService;

@RequestMapping("/auth")
@RestController
public class LoginController {

    private @Autowired ILoginService loginService;
    
    @PostMapping("/login")
    public LoginResponse login(@RequestBody Login login)
    {
        return loginService.verificarIngreso(login);
    }
}
