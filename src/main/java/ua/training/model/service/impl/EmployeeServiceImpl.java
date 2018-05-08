package ua.training.model.service.impl;

import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;
import ua.training.constant.LogMessage;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.EmployeeDao;
import ua.training.model.service.EmployeeService;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Optional;
import java.util.Random;

public class EmployeeServiceImpl implements EmployeeService {
    private static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);
    private final ThreadLocal<Random> random = new ThreadLocal<>();

    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(connection)) {
            return Optional.ofNullable(employeeDao.findByEmail(email));
        } catch (Exception e) {
            logger.error(LogMessage.NO_RESULT_FROM_DB);
        }
        return Optional.empty();
    }

    @Override
    public void registerDriver(Driver driver) throws Exception {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(connection)){
            driver.setRole(Employee.ROLE.DRIVER);
            driver.setPassword(makePasswordHash(driver.getPassword(), Integer.toString(getRandom().nextInt())));
            employeeDao.create(driver);
        } catch (Exception e) {
            logger.error(LogMessage.DRIVER_REGISTRATION_ERROR, e);
            throw new Exception(e);
        }
    }

    private String makePasswordHash(String password, String salt) {
        try {
            String saltedAndHashed = password + "," + salt;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(saltedAndHashed.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
            return encoder.encode(hashedBytes) + "," + salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 unavailable", e);
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
