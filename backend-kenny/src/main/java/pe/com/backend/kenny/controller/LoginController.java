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
import pe.com.backend.kenny.service.ILoginService;

@RequestMapping("/login")
@RestController
public class LoginController {

    private @Autowired ILoginService loginService;
    
    @PostMapping
    public ResponseEntity<BaseResponse> login(@RequestBody Login login)
    {
        if(loginService.verificarIngreso(login))
        {
            return new ResponseEntity<BaseResponse>(BaseResponse.builder()
					.codRespuesta("0")
					.msjRespuesta("Exito")
					.build()
					, HttpStatus.OK);
        }else{
            return new ResponseEntity<BaseResponse>(BaseResponse.builder()
					.codRespuesta("1")
					.msjRespuesta("Usuario y/o contraseña no es correcta")
					.build()
					, HttpStatus.UNAUTHORIZED);
        }
    }
}
