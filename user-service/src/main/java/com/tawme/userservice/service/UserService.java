package com.tawme.userservice.service;

import com.tawme.userservice.dto.UserRequest;
import com.tawme.userservice.dto.UserResponse;
import com.tawme.userservice.dto.UsuarioCadastradoEvent;
import com.tawme.userservice.entity.User;
import com.tawme.userservice.mapper.UserMapper;
import com.tawme.userservice.repository.UserRepository;
import com.tawme.userservice.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final NotificacaoRabbitService notificacaoRabbitService;

    private final String exchangeNovoUsuario;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                       TokenService tokenService, NotificacaoRabbitService notificacaoRabbitService, @Value("${rabbitmq.novousuario.exchange}") String exchangeNovoUsuario) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchangeNovoUsuario = exchangeNovoUsuario;

    }

    public UserResponse createUser(UserRequest userRequest) {
        User newUser = UserMapper.INSTANCE.convertUserRequestToUser(userRequest);
        String ecryptedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(ecryptedPassword);
        userRepository.save(newUser);

        var evento = new UsuarioCadastradoEvent(
                newUser.getId(),
                newUser.getName(),
                newUser.getPhoneNumber()
        );

        notificarRabbitMQ(evento);

        return UserMapper.INSTANCE.convertUserToUserResponse(newUser);
    }

    public String login(String phoneNumber, String password) {
        var user = userRepository.findByPhoneNumber(phoneNumber);

        if (user == null) {
            throw new RuntimeException("User not found");
        } else {
            try {
                var usernamePassword = new UsernamePasswordAuthenticationToken(phoneNumber, password);
                var auth = authenticationManager.authenticate(usernamePassword);

                var userFound  = (User) auth.getPrincipal();

                return tokenService.generateToken(userFound.getId(), userFound.getPhoneNumber());

            } catch (RuntimeException e) {
                return "Falha na autenticação: " + e.getMessage();
            }
        }
    }

    private void notificarRabbitMQ(UsuarioCadastradoEvent user) {
        try{
            notificacaoRabbitService.notificar(user, exchangeNovoUsuario);
        }catch (RuntimeException e){
            System.out.println("Erro ao notificar");
        }
    }
}
