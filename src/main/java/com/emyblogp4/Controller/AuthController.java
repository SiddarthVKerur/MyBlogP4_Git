package com.emyblogp4.Controller;

import com.emyblogp4.Entity.User;
import com.emyblogp4.Repository.UserRepository;
import com.emyblogp4.payload.SignInDto;
import com.emyblogp4.payload.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
 		//(if error coming then remove this annotation but not below line)
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is already exist" + signUpDto.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("signup successfully", HttpStatus.CREATED);
    }


    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody SignInDto signInDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(signInDto.getUsernameOrEmail(), signInDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new ResponseEntity<>("User sign-in successfully", HttpStatus.OK);
    }

    @PutMapping("/getmap")
    public ResponseEntity<String> editString(@PathVariable String name) {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
    @DeleteMapping("/deleteMap")
    public ResponseEntity<String> deleteString(@PathVariable String firstName) {
        return new ResponseEntity<>(firstName, HttpStatus.OK);
//        System.out.println("welcome to java"):q
//        System.out.println("ram");
//        System.out.println("raj");
//      "hello world"
//        learning java
        // welcome to class

//       "OOPs concept"
    }
}

