package br.unitins.tp1.pizzaria.service;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@ApplicationScoped
public class HashServiceImpl implements HashService{
    private String salt = "ja8souihgfd8iusahbf98sdfy";
    private final Integer iterations = 405;

    private final Integer keyLength = 512;

    @Override
    public String getHash(String senha) {
        try {
            byte[] result =  SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret( new PBEKeySpec(senha.toCharArray(),
                            salt.getBytes(), iterations, keyLength)).getEncoded();

            return Base64.getEncoder().encodeToString(result);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            Log.error(e);
            throw new RuntimeException("Erro ao criar um hash");
        }

    }
}
