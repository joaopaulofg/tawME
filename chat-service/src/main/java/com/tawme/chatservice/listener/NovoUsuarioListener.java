package com.tawme.chatservice.listener;

import com.tawme.chatservice.dto.UsuarioCadastradoEvent;
import com.tawme.chatservice.entity.User;
import com.tawme.chatservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NovoUsuarioListener {

    UserRepository userRepository;

    @RabbitListener(queues = "${rabbitmq.novousuario.queue}")
    public void novoUsuario(UsuarioCadastradoEvent event) {
        User user = new User();
        user.setId(event.id());
        user.setName(event.name());
        user.setPhoneNumber(event.phoneNumber());

        userRepository.save(user);
    }
}

