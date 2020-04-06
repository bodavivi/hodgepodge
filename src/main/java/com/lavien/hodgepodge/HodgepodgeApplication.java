package com.lavien.hodgepodge;

import com.lavien.hodgepodge.repositories.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HodgepodgeApplication{
  @Autowired
  MerchantRepository merchantRepository;

  public static void main(String[] args) {
    SpringApplication.run(HodgepodgeApplication.class, args);
  }

}
