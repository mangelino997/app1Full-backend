/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.angular.utils;

/**
 *
 * @author Marcio
 */
public class Constants {

    /*
    public static final String REQUEST_TOKEN_URL = "/login";

    public static final String AUTHORITIES_KEY = "CLAIM_TOKEN";

    public static final String SIGNING_KEY = "KEY_1234";

    public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 28800;

    public static final String ISSUER_TOKEN = "ISSUER";

    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";

    public static final String TOKEN_BEARER_PREFIX = "Bearer";

    public static final String ISSUER_INFO = "https://www.angularFullStack1.com/"; */
    public static final String AUTHORITIES_KEY = "CLAIM_TOKEN";
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";

}
