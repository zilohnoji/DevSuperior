package com.donatoordep.lesson01;

import com.donatoordep.lesson01.entities.Order;
import com.donatoordep.lesson01.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class Lesson01Application implements CommandLineRunner {

    @Autowired
    private OrderService service;

    public static void main(String[] args) {
        SpringApplication.run(Lesson01Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);

        System.out.print("Digite o código do produto: ");
        Integer code = sc.nextInt();

        System.out.print("Digite o valor do produto: ");
        Double basic = sc.nextDouble();

        System.out.print("Digite o desconto do produto: ");
        Double discount = sc.nextDouble();

        System.out.printf("Pedido código: %d\nValor total: R$%.2f", code,
                service.total(new Order(code, basic, discount)));
    }
}
