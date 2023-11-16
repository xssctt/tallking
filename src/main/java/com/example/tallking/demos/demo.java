package com.example.tallking.demos;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.Base64;

public class demo {
        public static void main(String[] args) throws Exception {

            //导入所需的JWT相关类库,包括JWT、算法、异常等。
            //main方法中首先用KeyPairGenerator生成一对RSA公私钥。
            //在try-catch块中,使用RSA256算法和私钥生成一个新的JWT,并打印出来。
            //然后准备一个已存在的JWT样本token,并验证它。
            //创建RSA256算法实例,传入公钥和私钥。
            //使用JWT.require()指定验证约束,如issuer。
            //build()创建verifier实例并验证token。
            //验证成功会返回DecodedJWT对象。
            //最后打印"合法的Token"。
            //generateRsaKey()方法 simply生成RSA密钥对。

            // 生成RSA密钥对
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

            // 初始化参数
            RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(2048, RSAKeyGenParameterSpec.F4);

            // 初始化密钥对生成器
            keyGen.initialize(spec);

            // 生成密钥对
            KeyPair pair = keyGen.generateKeyPair();

            // 获取RSAPublicKey
            RSAPublicKey publicKey = (RSAPublicKey) pair.getPublic();

            // 获取RSAPrivateKey
            RSAPrivateKey privateKey = (RSAPrivateKey) pair.getPrivate();

//            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
//
//            // 初始化密钥对生成器
//            keyGen.initialize(2048);
//
//            // 生成RSA密钥对
//            KeyPair pair = keyGen.generateKeyPair();
//
//            // 获取私钥
//            PrivateKey privateKey = pair.getPrivate();
//
//            // 获取公钥
//            PublicKey publicKey = pair.getPublic();
//
//            //打印公私钥
//            System.out.println("Public Key: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
//            System.out.println("Private Key: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

            // 创建JWT
            try {

                Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);

                String token = JWT.create()
                        .withIssuer("auth0")
                        .sign(algorithm);

                System.out.println("生成的Token: " + token);

            } catch (JWTCreationException exception){
                // Invalid Signing configuration
            }

            // 验证JWT生成的Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoMCJ9.qb3xIsH2mL0G1YkruXtLfVDbxhO_ejYOMN6PWpZztCTti5MqR2185vdzEBJA5gY29fBzWbNuWmYAmtq4Qx0ziNj-JmyMm-fqVDZpujJeb8Jjkm9YmxxT6Oi6oUYX_4K_bV74gK-W95jdmomXIvXgn4scP7n7s6Mj7PV47EzwZ_bGJAfQCdbgILOkx-19SUF9lznh3lCeBOkNZ5uEPVhJl86AxnBzWlz-kz2MfTclu6rE4KNY8r5hFBHdv_BmQkY9FPixdYWiK2OWaDgWQJJpRaCzPxG-SVc-kId3GewsUtJHU0ugiXrCzNH2Z03NVJswwPNJnC6fJFFoI68I58YJPA
            String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoMCJ9.GLKYVlsfLzJ4XrmECP9vxI7MDJqCuyhZUffAvNeTptZ3MBPni_Loi73GMPVPBMWdW9lcvDVPHNq2HFnbuT6M0ZsXU0d6D9KAuN-h3dUqF-c7Tkkn3v9uphZjMtYGVHn6inw2teM8kcHCZGck6m4QhKspTuyEg-UpzFIvqnnakPs9gF_l7P36NonwHunH7ySlrABKIl32rOFwXiUvvAd1eNWmPkeYCIXJ8FohXvZSmKuXzuLTddezIxDEmpGIc-eDJ1xmP4FdJuMdsnyT-cEYCWvgGn7HzWVE7612hkPD2rbKKB6uPB68GSNlee-qk3tQMvtZpF7QlT1uzuFiX-nIOA";
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();

            DecodedJWT jwt = verifier.verify(token);

            System.out.println("合法的Token");
        }

        private static KeyPair generateRsaKey() throws Exception {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            return keyPairGen.generateKeyPair();
        }

    }


