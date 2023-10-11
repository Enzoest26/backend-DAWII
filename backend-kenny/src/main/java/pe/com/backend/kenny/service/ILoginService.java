package pe.com.backend.kenny.service;

import pe.com.backend.kenny.model.request.Login;

public interface ILoginService {
    
    public boolean verificarIngreso(Login login);
    
}
