package com.component;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;

/**
 * @author Ian.Su
 * @version $Id: PlaintextPasswordEncoderTest.java, v 0.1 2017/7/12 14:15 Ian.Su Exp $
 */
public class PlaintextPasswordEncoderTest {

    public static void main(String [] agrs){

        Md5PasswordEncoder a = new Md5PasswordEncoder();

        System.out.println( a.encodePassword("fads","fd") );

    }

}
