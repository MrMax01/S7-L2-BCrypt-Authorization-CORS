package massimomauro.S7L2BCryptAuthorizationCORS.controllers;


import massimomauro.S7L1SpringSecurityJWT.entities.User;
import massimomauro.S7L1SpringSecurityJWT.exceptions.BadRequestException;
import massimomauro.S7L1SpringSecurityJWT.payloads.NewUserDTO;
import massimomauro.S7L1SpringSecurityJWT.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    // 1. GET http://localhost:3001/users (+ query params opzionali)
    @GetMapping("")
    public Page<User> getUser(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String orderBy){
        return usersService.getUsers(page, size, orderBy);
    }

    // 2. POST http://localhost:3001/users (+ body)
    @PostMapping("")
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

    // 3. GET http://localhost:3001/users/:id
    @GetMapping(value = "/{id}")
    public User findById(@PathVariable int id){
        return usersService.findById(id);
    }

    // 4. PUT http://localhost:3001/users/:id (+ body)
    @PutMapping("/{id}")
    public User findByIdAndUpdate(@PathVariable int id, @RequestBody User body){
        return usersService.findByIdAndUpdate(id, body);
    }

    // 5. DELETE http://localhost:3001/users/:id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findByIdAndDelete(@PathVariable int id){
        usersService.findByIdAndDelete(id);
    }

    @PostMapping("/upload")
    public String uploadExample(@RequestParam("avatar") MultipartFile body) throws IOException {

        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return usersService.uploadPicture(body);
    }

    @PutMapping("/{id}/avatar")
    public User findUserByIdAndUploadAvatar(@PathVariable int id, @RequestParam("avatar") MultipartFile body)  throws IOException{
        return  usersService.findUserByIdSetAvatar(id,body);
    }

    @GetMapping("/{id}/avatar")
    public String findAvatarById(@PathVariable int id){

        return usersService.findAvatarById(id);
    }





}
