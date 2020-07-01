import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import models.CredentialRequestModel;
import models.SessionRequestModel;
import models.PrivilegeRequestModel;
import models.ResponseModel;
import util.Utility;

@RestController
@EnableAutoConfiguration
@RequestMapping("idm")
public class Idm {

    @PostMapping("register")
    ResponseEntity<ResponseModel> register(@RequestBody CredentialRequestModel requestModel) {
        return Utility.registerUser(requestModel);
    }

    @PostMapping("login")
    ResponseEntity<ResponseModel> login(@RequestBody CredentialRequestModel requestModel) {
        return Utility.loginUser(requestModel);
    }
    // TODO
    @PostMapping("session")
    ResponseEntity<ResponseModel> session(@RequestBody SessionRequestModel requestModel) {
        return Utility.verifySession(requestModel);
    }
    // TODO
    @PostMapping("privilege")
    ResponseEntity<ResponseModel> privilege(@RequestBody PrivilegeRequestModel requestModel) {
        return Utility.checkPrivilege(requestModel);
    }

    public static void main(String[] args) {
        SpringApplication.run(Idm.class, args);
    }

}