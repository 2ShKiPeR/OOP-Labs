package ru.ssau.tk.jabalab.lr2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.ssau.tk.jabalab.lr2.model.LoginRequest;
import ru.ssau.tk.jabalab.lr2.model.User;
import ru.ssau.tk.jabalab.lr2.repository.UserRepository;
import ru.ssau.tk.jabalab.lr2.security.JwtResponse;
import ru.ssau.tk.jabalab.lr2.security.JwtUtils;

public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/auth/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String jwt = jwtUtils.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @DeleteMapping("/auth/delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username, @AuthenticationPrincipal UserDetails currentUser) {
        if (!currentUser.getUsername().equals(username)) {
            return ResponseEntity.badRequest().body("Error: You can only delete your own account.");
        }

        return userRepository.findByUsername(username).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok("User deleted successfully!");
        }).orElse(ResponseEntity.badRequest().body("Error: User not found"));
    }
}
