package com.hse.organizer.rest;

import com.hse.organizer.dto.AuthenticationRequestDto;
import com.hse.organizer.dto.RegisterUserRequestDto;
import com.hse.organizer.dto.ResponseLoginDto;
import com.hse.organizer.model.Role;
import com.hse.organizer.model.User;
import com.hse.organizer.security.jwt.JwtTokenProvider;
import com.hse.organizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for authentication requests (login, logout, register, etc.)
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    /**
     * @param requestDto - user login, password
     * @return JWT Token
     */
    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null)
                throw new UsernameNotFoundException("User with username: " + username + " not found");

            List<Role> roleList = user.getRoleList();
            String token = jwtTokenProvider.createToken(username, roleList);

            ResponseLoginDto response = new ResponseLoginDto();
            response.setUsername(username);
            response.setToken(token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    /**
     * This method just add user to data base
     * @param requestDto user info
     * @return String if everything is ok
     */
    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterUserRequestDto requestDto) {
        try {
            User user = requestDto.toUser();
            User result = userService.register(user);

            if (result == null)
                throw new UsernameNotFoundException("Invalid login or password");

            return ResponseEntity.ok("User with username: " + user.getUsername() + " and id: " + user.getId()
                    + " was registered successfully");
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid data");
        }
    }
}
