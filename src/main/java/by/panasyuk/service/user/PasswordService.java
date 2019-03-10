package by.panasyuk.service.user;

import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.specification.user.GetByLogin;
import by.panasyuk.repository.specification.Specification;
import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ArgumentCorrectException;
import by.panasyuk.service.exception.ServiceException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PasswordService extends UserService {

    public String changePassword(String login, String email) throws ServiceException, ArgumentCorrectException {
        PresentChecker presentChecker = new PresentChecker();
        if (!presentChecker.isReservedLogin(login)) {
            throw new ArgumentCorrectException();
        }
        User user = new User();
        user.setLogin(login);
        try {
            Specification<User> spec = new GetByLogin();
            List<User> list = userRepository.getQuery(user, spec);
            user = list.get(0);
            if (!user.getEmail().equals(email)) {
                throw new ArgumentCorrectException();
            }
            String newPassword = Long.toHexString(Double.doubleToLongBits(Math.random()));
            user.setPassword(passwordHash(newPassword));
            userRepository.update(user);
            return newPassword;
        } catch (RepositoryException |NoSuchAlgorithmException e) {
            throw new ServiceException("Failed to change user password. ", e);
        }
    }
    public  String passwordHash(String password) throws NoSuchAlgorithmException{
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] passwordByte = sha256.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < passwordByte.length; i++){
            String s = Integer.toHexString(passwordByte[i]&0xff);
            if(s.length() == 1){
                s = "0" + s;
            }
            sb.append(s);
        }
        return sb.toString();
    }

}