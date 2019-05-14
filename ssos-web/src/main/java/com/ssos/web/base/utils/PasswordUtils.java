package com.ssos.web.base.utils;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @ClassName: PasswordUtils
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018-12-25 09:59
 * @Vsersion: 1.0
 */

@Component
@Lazy
public class PasswordUtils {

    @Autowired
    private HashedCredentialsMatcher hashedCredentialsMatcher;

    public String getSalt(){
       return  new SecureRandomNumberGenerator().nextBytes().toHex();
    }
    public  String getPasswordEncryption(String password,String salt){
       return new SimpleHash(hashedCredentialsMatcher.getHashAlgorithmName(),
                password,salt, hashedCredentialsMatcher.getHashIterations()).toHex();
    }
}
