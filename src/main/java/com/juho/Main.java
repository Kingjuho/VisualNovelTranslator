package com.juho;

import java.io.InputStream;
import java.util.Properties;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;


public class Main {
    // Gemini API constant
    private static String API_URL;
    private static String API_KEY;
    // Properties constant
    private static final String pTEST = "test.test";
    private static final String pAPI_URL = "gemini.api.url";
    private static final String pAPI_KEY = "gemini.api.key";

    public static void propSettings() {
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

        API_KEY = props.getProperty(pAPI_KEY);
        if (API_KEY == null || API_KEY.isBlank()) {
            System.err.println("application.properties에" + pAPI_KEY + "를 설정해주세요.");
            System.exit(1);
        }
        API_URL = props.getProperty(pAPI_URL);
        if (API_URL == null || API_URL.isBlank()) {
            System.err.println("application.properties에" + API_URL + "를 설정해주세요.");
            System.exit(1);
        }
    }

    public static void GenerateTextFromTextInput(String param) {
        // The client gets the API key from the environment variable `GOOGLE_API_KEY`.
        Client client;
        try {
            client = Client.builder().apiKey(API_KEY).build();
            GenerateContentResponse response = client.models.generateContent("gemini-2.0-flash", param,null);

            System.out.println(response.text());
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        propSettings();
        GenerateTextFromTextInput("안녕하세요!");
    }
}