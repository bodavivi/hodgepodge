package com.lavien.hodgepodge.services;

import com.lavien.hodgepodge.models.merchants.Merchant;
import com.lavien.hodgepodge.repositories.MerchantRepository;
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
      // TODO should define two basic card per player (max. 10, 8 is missing)
      // TODO nem ID alapján kellene szűrni, hanem obtain_root = 2 és number_of_updates = 2
      if (merchant.getId() != 1 || merchant.getId() != 2) {
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
    // TODO nem ID alapján kellene belerakni őket
    starterCards.add(merchantRepository.findById(1L).orElse(null));
    starterCards.add(merchantRepository.findById(2L).orElse(null));
    return starterCards;
  }
}
