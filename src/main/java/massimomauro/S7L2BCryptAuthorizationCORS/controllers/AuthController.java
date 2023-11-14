package massimomauro.S7L2BCryptAuthorizationCORS.controllers;

import massimomauro.S7L2BCryptAuthorizationCORS.entities.User;
import massimomauro.S7L2BCryptAuthorizationCORS.exceptions.BadRequestException;
import massimomauro.S7L2BCryptAuthorizationCORS.payloads.NewUserDTO;
import massimomauro.S7L2BCryptAuthorizationCORS.payloads.UserLoginDTO;
import massimomauro.S7L2BCryptAuthorizationCORS.payloads.UserLoginSuccessDTO;
import massimomauro.S7L2BCryptAuthorizationCORS.services.AuthService;
import massimomauro.S7L2BCryptAuthorizationCORS.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public UserLoginSuccessDTO login(@RequestBody UserLoginDTO body){

        return new UserLoginSuccessDTO(authService.authenticateUser(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public User saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return usersService.save(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
