package com.eduardo.stock_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void enviarAlertaEstoqueMinimo(String destinatario, String assunto, String mensagem) {
        SimpleMailMessage mensagem1 = new SimpleMailMessage();
        mensagem1.setTo(destinatario);
        mensagem1.setSubject(assunto);
        mensagem1.setText(mensagem);

        mailSender.send(mensagem1);
    }
}
