package com.urlshortener;

import com.urlshortener.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class URLShortenerApplication implements CommandLineRunner {

    private final  ShortLinkService shortLinkService;

    public static void main(String[] args) {
        SpringApplication.run(URLShortenerApplication.class, args);
    }

    @Override
    public void run(String... args){
        System.out.println("Please,enter command:" +
                           "\n- Shortening of original URL by using provided SEO keyword: 1" +
                           "\n- Shortening of original URL, without provided keyword: 2" +
                           "\n- Retrieve original URL by shorten URL: 3" +
                           "\n- Showing list of all short links: 4" +
                           "\n- Exit application: 0");

        while(true){
            Scanner scanner = new Scanner(System.in);
            int command = scanner.nextInt();

            switch (command) {
                case 0:
                    System.exit(0);
                case 1:
                    shortLinkService.createShortLinkBasedOnKeyword();
                    break;
                case 2:
                    shortLinkService.createShortLink();
                    break;
                case 3:
                    shortLinkService.printLongLink();
                    break;
                case 4:
                    shortLinkService.printListOfShortLinks();
                    break;
                default:
                    System.out.println("This command doesn't exist");
            }
        }
    }
}
