package massimomauro.S7L2BCryptAuthorizationCORS.services;

import massimomauro.S7L1SpringSecurityJWT.entities.User;
import massimomauro.S7L1SpringSecurityJWT.exceptions.UnauthorizedException;
import massimomauro.S7L1SpringSecurityJWT.payloads.UserLoginDTO;
import massimomauro.S7L1SpringSecurityJWT.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body){
        // 1. Verifichiamo che l'email dell'utente sia nel db
        User user = usersService.findByEmail(body.email());

        // 2. In caso affermativo, verifichiamo se la password corrisponde a quella trovata nel db
        if(body.password().equals(user.getPassword())) {
            // 3. Se le credenziali sono OK --> Genero un JWT e lo restituisco
            return jwtTools.createToken(user);
        } else {
            // 4. Se le credenziali NON sono OK --> 401
            throw new UnauthorizedException("Credenziali non valide!");
        }

    }
}
