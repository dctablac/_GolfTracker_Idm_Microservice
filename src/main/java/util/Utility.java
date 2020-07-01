package util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.security.SecureRandom;
import models.*;
import java.util.Arrays;

public class Utility {

    public static ResponseEntity<ResponseModel> registerUser(CredentialRequestModel requestModel) {
        // Do validation on email and password and add to database
        String email = requestModel.getEmail();
        String password = requestModel.getPassword();

        ResponseModel responseModel = null;
        if (!Utility.isValidEmail(email)) {
            responseModel = new ResponseModel(-11, "Email address has invalid format.", null);
        }
        else if (email.length() < 10) {
            responseModel = new ResponseModel(-10, "Email address has invalid length", null);
        }
        else {
            // Query to add user to database
            boolean newUserSuccess = Query.addUser(email, password);
            if (newUserSuccess) {
                System.err.println("User registered successfully.");
                responseModel = new ResponseModel(110, "User registered successfully.", null);
            }
            else {
                responseModel = new ResponseModel(16, "Email aleady in use.", null);
            }
        }
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseModel> loginUser(CredentialRequestModel requestModel) {
        String email = requestModel.getEmail();
        String password = requestModel.getPassword();
        

        ResponseModel responseModel = null;
        if (!Utility.isValidEmail(email)) {
            responseModel = new ResponseModel(-11, "Email address has invalid format.", null);
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.BAD_REQUEST);
        }
        else if (email.length() < 10) {
            responseModel = new ResponseModel(-10, "Email address has invalid length.", null);
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.BAD_REQUEST);
        }
        else if (!Query.emailExists(email)) {
            responseModel = new ResponseModel(-14, "Account with that email does not exist.", null);
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.BAD_REQUEST);
        }
        else if (Query.loginSuccess(email, password) == true) {
            String sessionId = Utility.generateToken();
            responseModel = new ResponseModel(120, "User logged in successfully.", sessionId);
        }
        else {
            responseModel = new ResponseModel(121, "Email and password do not match.", null);
        }
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseModel> verifySession(SessionRequestModel requestModel) {
        String email = requestModel.getEmail();
        String session_id = requestModel.getSessionId();
        
        ResponseModel responseModel = null;
        if (session_id.length() != 64) {
            responseModel = new ResponseModel(-13, "Token has invalid length.", null);
        }
        else if (!isValidEmail(email)) {
            responseModel = new ResponseModel(-11, "Email address has invalid format.", null);
        }
        else if (email.length() < 10){
            responseModel = new ResponseModel(-10, "Email address has invalid length.", null);
        }
        else if (session_id.equals(null)) {
            responseModel = new ResponseModel(134, "Session not found", null);
        }
        // TODO: expired, active, revoked
        else {
            responseModel = new ResponseModel(130, "Session is active", session_id);
        }
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseModel> checkPrivilege(PrivilegeRequestModel requestModel) {
        Integer plevel = requestModel.getPlevel();
        
        ResponseModel responseModel = null;
        if (plevel < 1 || plevel > 5) {
            responseModel = new ResponseModel(-14, "Privilege level out of range.", null);
        }
        // TODO plenty.
        else {
            responseModel = new ResponseModel(140, "User has sufficient privilege level.", null);
        }
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }




    ////////////////////////////////////////////////////////////////////

    public static boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]+");
    }

    public static String generateToken() {
        final SecureRandom rngesus = new SecureRandom();
        final int tokenSize = 64;

        byte[] tokenArray = new byte[tokenSize];
        rngesus.nextBytes(tokenArray);

        StringBuffer buf = new StringBuffer();
        for (byte b : tokenArray) {
            buf.append(format(Integer.toHexString(Byte.toUnsignedInt(b))));
        }
        return buf.toString();
    }

    public static String format(String binS) {
        int length = 2 - binS.length();
        char[] padArray = new char[length];
        Arrays.fill(padArray, '0');
        String padString = new String(padArray);
        return padString + binS;
    }
}