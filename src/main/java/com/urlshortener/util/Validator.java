package com.urlshortener.util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String URL_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";

    private static Pattern URL_PATTERN = Pattern.compile(URL_REGEX);
    private static Scanner scanner = new Scanner(System.in);

    private Validator() {}

    private static boolean isValidURL(String url) {
        Matcher m = URL_PATTERN.matcher(url);
        return m.matches();
    }

    private static boolean isValidKeyword(String keyword){
        return keyword.length() <=20;
    }

    public static String[] getValidInput(boolean isKeywordNeeded){
        String [] inputData = new String[2];
        System.out.println("Enter the link below:");
        String longLink = scanner.nextLine();
        if(!isValidURL(longLink)){
            throw new IllegalArgumentException("Link is not valid.");
        }
        inputData[0] = longLink;

        if(isKeywordNeeded){
            System.out.println("Enter SEO keyword:");
            String keyword = scanner.nextLine();
            if(!isValidKeyword(keyword)){
                throw new IllegalArgumentException("Keyword is not valid.");
            }

            inputData[1] = keyword;
        }

        return inputData;
    }
}
