package com.lavien.hodgepodge.services;

import com.lavien.hodgepodge.models.merchants.Merchant;
import com.lavien.hodgepodge.repositories.MerchantRepository;
import java.util.Arrays;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {

  private final MerchantRepository merchantRepository;

  @Autowired
  public MerchantServiceImpl(MerchantRepository merchantRepository) {
    this.merchantRepository = merchantRepository;
  }

  @Override
  public List<Merchant> findAll() {
    List<Merchant> allMerchant = new ArrayList<>();
    for (Merchant merchant : merchantRepository.findAll()) {
      allMerchant.add(merchant);
    }
    return allMerchant;
    // or: return new ArrayList<>(merchantRepository.findAll());
  }

  @Override
  public List<Merchant> findStarterUnavailableMerchants() {
    List<Merchant> merchants = new ArrayList<>();
    for (Merchant merchant : merchantRepository.findAll()) {
      if (merchant.getId() > 10) {
        merchants.add(merchant);
      }
    }
    return merchants;
  }

  @Override
  public Merchant save(Merchant merchant) {
    return merchantRepository.save(merchant);
  }

  @Override
  public List<Merchant> pickUpStarterCards() {
    List<Merchant> starterCards = new ArrayList<>();
    // mindig 10 basic kártya van
    for (long i = 1; i <= 10; i++) {
      starterCards.add(merchantRepository.findById(i).orElse(null));
    }
    return starterCards;
  }
}
