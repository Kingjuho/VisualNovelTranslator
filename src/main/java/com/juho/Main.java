package com.juho;

import java.util.Properties;
import java.io.InputStream;


public class Main {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static String API_KEY;

    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream in = Main.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (in == null) {
                System.err.println("application.properties 파일을 찾을 수 없습니다.");
                System.exit(1);
            }
            props.load(in);
        } catch (Exception e) {
            System.err.println("Property 로드 중 오류: " + e.getMessage());
            System.exit(1);
        }

        API_KEY = props.getProperty("openai.api.key");
        if (API_KEY == null || API_KEY.isBlank()) {
            System.err.println("application.properties에 openai.api.key를 설정해주세요.");
            System.exit(1);
        }

        System.out.println(API_KEY);
    }
}