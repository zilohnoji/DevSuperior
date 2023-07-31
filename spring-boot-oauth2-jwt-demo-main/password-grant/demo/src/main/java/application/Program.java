package application;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class Program {


    public static void main(String[] args) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        // Criando um codificador com força 10
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

        // Criando um codificador padrão
        Argon2PasswordEncoder encoder2 = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

        encoders.put("bcrypt", encoder);
        encoders.put("argon2", encoder2);

        System.out.println(new DelegatingPasswordEncoder("argon2", encoders).encode("myPassword"));
    }
}
