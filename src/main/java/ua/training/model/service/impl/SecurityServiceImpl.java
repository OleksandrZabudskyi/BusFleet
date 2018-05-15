package ua.training.model.service.impl;

import ua.training.constant.Attributes;
import ua.training.constant.Messages;
import ua.training.model.service.SecurityService;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class SecurityServiceImpl implements SecurityService {
    private final ThreadLocal<Random> random = new ThreadLocal<>();

    @Override
    public String makePasswordHash(String password) {
        return makePasswordHash(password, Integer.toString(getRandom().nextInt()));
    }

    @Override
    public boolean comparePasswords(String password, String hashedAndSaltedPassword) {
        String salt = hashedAndSaltedPassword.split(Attributes.COMMA_SIGN)[1];
        return hashedAndSaltedPassword.equals(makePasswordHash(password, salt));
    }


    private String makePasswordHash(String password, String salt) {
        try {
            String saltedAndHashed = password.concat(Attributes.COMMA_SIGN).concat(salt);
            MessageDigest digest = MessageDigest.getInstance(Attributes.MD5);
            digest.update(saltedAndHashed.getBytes());
            byte hashedBytes[] = (new String(digest.digest(), Attributes.UTF8)).getBytes();
            String encode = Base64.getEncoder().encodeToString(hashedBytes);
            return encode.concat(Attributes.COMMA_SIGN).concat(salt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(Messages.MD5_IS_NOT_AVAILABLE, e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(Messages.UTF8_IS_NOT_AVAILABLE, e);
        }
    }

    private Random getRandom() {
        Random result = random.get();
        if (result == null) {
            result = new Random();
            random.set(result);
        }
        return result;
    }
}
