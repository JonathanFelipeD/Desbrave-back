package com.Desbrave.Desbrave.service.IMPL;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {

    private final JavaMailSender javaMailSender;
    private final Environment env;

    public void enviarEmail(String email, String token){
        String linkResetSenha = env.getProperty("app.url") + "/recuperar-senha?token=" + token;
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Recuperação de Senha");
        message.setText("Clique no link para redefinir sua senha: " + linkResetSenha);

        javaMailSender.send(message);
    }

}
