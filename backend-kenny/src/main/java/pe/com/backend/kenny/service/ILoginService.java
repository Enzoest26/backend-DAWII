package pe.com.backend.kenny.service;

import pe.com.backend.kenny.model.request.Login;
import pe.com.backend.kenny.model.response.LoginResponse;

public interface ILoginService {
    
    public LoginResponse verificarIngreso(Login login);
    
}
