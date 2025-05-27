package com.tawme.chatservice;

import com.tawme.chatservice.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/teste")
public class TesteController {

    private final AuthenticatedUser authenticatedUser;

    @GetMapping
    public ResponseEntity<String> test(Authentication auth) {
//        var usuarioLogado = auth.getClass();
//        System.out.println(usuarioLogado);
        return ResponseEntity.ok("Token válido! Acesso autorizado.");
    }

    @GetMapping("/me")
    public String getLoggedUser() {
        return "Usuário autenticado: " + authenticatedUser.getCurrentUserId();
    }
}
