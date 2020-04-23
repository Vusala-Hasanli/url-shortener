package com.urlshortener.service;

import com.urlshortener.model.ShortLink;
import com.urlshortener.repository.ShortLinkRepository;
import com.urlshortener.util.IDConverter;
import com.urlshortener.util.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShortLinkService {
   private final ShortLinkRepository shortLinkRepository;
   private static final String shortLinkProviderDomain = "sage.ru";

   public void createShortLinkBasedOnKeyword(){
       try {
           String[] inputData = Validator.getValidInput(true);
           String longLink = inputData[0];
           String keyword = inputData[1];
           if(shortLinkRepository.findByKeyword(keyword) == null){
               ShortLink shortLink = new ShortLink();
               shortLink.setLongLink(longLink);
               shortLink.setKeyword(keyword);
               shortLinkRepository.save(shortLink);

               System.out.println(shortLinkProviderDomain + "/" + keyword);
           }else {
               System.out.println("This keyword already exists.");
           }

       }catch (IllegalArgumentException e){
           System.out.println(e.getMessage());
       }
   }

    public void createShortLink(){
       try {
           String longLink = Validator.getValidInput(false)[0];
           ShortLink shortLink = shortLinkRepository.findByLongLink(longLink);
           if( shortLink!= null){
               System.out.println("You have already created short version of this link: "
                                  + shortLinkProviderDomain + "/" + shortLink.getKeyword());
           }else{
               ShortLink newShortLink = new ShortLink();
               newShortLink.setLongLink(longLink);
               newShortLink = shortLinkRepository.save(newShortLink);
               String keyword = IDConverter.createKeyword(newShortLink.getId());
              // String keyword = IDConverter.generateRandomKeyword(); // second way
               newShortLink.setKeyword(keyword);
               shortLinkRepository.save(newShortLink);

               System.out.println(shortLinkProviderDomain + "/" + keyword);
           }

       }catch (IllegalArgumentException e){
           System.out.println(e.getMessage());
       }
    }

    public void printLongLink(){
       try{
           String input = Validator.getValidInput(false)[0];
           String keyword = input.split("/")[1];
           ShortLink shortLink = shortLinkRepository.findByKeyword(keyword);
           if(shortLink != null)
               System.out.println(shortLink.getLongLink());
           else
               System.out.println("There is no data for this input in our system");

       }catch (IllegalArgumentException e){
           System.out.println(e.getMessage());
       }
    }

    public void printListOfShortLinks(){
       List<ShortLink> list = shortLinkRepository.findAll();
       if(list.size()>0){
           list.forEach(shortLink -> System.out.println("Long version: " + shortLink.getLongLink() +
                                                        " Short version: " + shortLinkProviderDomain + "/" + shortLink.getKeyword()));
       }else
           System.out.println("There is no data in our system.");
    }
}
