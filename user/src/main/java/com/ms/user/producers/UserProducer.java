package com.ms.user.producers;

import com.ms.user.dtos.EmailDto;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserProducer {
    public static final String CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro Realizado com Sucesso";
    public static final String SEJA_BEM_VINDO = " Seja Bem Vindo!!!";
    private final RabbitTemplate rabbitTemplate;
    @Value("${broker.queue.email.name}")
    private String routeKey;

    @Autowired
    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessage(UserModel userModel) {
        var message = EmailDto.builder()
                .userId(userModel.getId())
                .name(userModel.getName())
                .emailTo(userModel.getEmail())
                .subject(CADASTRO_REALIZADO_COM_SUCESSO)
                .text(userModel.getName().concat(SEJA_BEM_VINDO))
                .registerDate(LocalDate.now())
                .build();

        rabbitTemplate.convertAndSend("", routeKey, message);
    }

}
