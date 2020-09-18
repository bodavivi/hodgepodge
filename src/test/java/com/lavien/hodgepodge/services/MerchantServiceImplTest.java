package com.lavien.hodgepodge.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lavien.hodgepodge.models.merchants.Merchant;
import com.lavien.hodgepodge.models.merchants.ObtainIngrMerchant;
import com.lavien.hodgepodge.models.merchants.UpdateIngrMerchant;
import com.lavien.hodgepodge.repositories.MerchantRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

public class MerchantServiceImplTest {

  private MerchantServiceImpl merchantService;
  private MerchantRepository merchantRepository;
  private List<Merchant> testMerchants;

  @Before
  public void setUp() {
    this.merchantRepository = mock(MerchantRepository.class);
    this.merchantService = new MerchantServiceImpl(merchantRepository);
    this.testMerchants = List.of(
        new ObtainIngrMerchant(2, 0, 0, 0),
        new UpdateIngrMerchant(2));
  }

  @Test
  public void findAll_ReturnsAllPredefinedMerchants_ThereAreTwoMerchants() {
    when(merchantRepository.findAll()).thenReturn(testMerchants);
    assertEquals(testMerchants, merchantService.findAll());
    verify(merchantRepository).findAll();
  }

  @Test
  public void findAll_ReturnsEmptyList_ThereIsNoMerchants() {
    when(merchantRepository.findAll()).thenReturn(new ArrayList<>());
    assertEquals(new ArrayList<>(), merchantService.findAll());
    verify(merchantRepository).findAll();
  }

  @Test
  public void save_ReturnsAndSavesTheMerchant_ThereIsAMerchant() {
    Merchant toSave = new UpdateIngrMerchant(7);

    when(merchantRepository.save(toSave)).thenReturn(toSave);
    assertEquals(toSave, merchantService.save(toSave));
    verify(merchantRepository).save(toSave);
  }

  @Test
  public void findStarterUnavailableMerchants_ReturnsAllMerchantExceptStarters_ThereIsADatabaseWithMerchants() {
    this.testMerchants.get(0).setId(11L);
    this.testMerchants.get(1).setId(12L);

    when(merchantRepository.findAll()).thenReturn(testMerchants);
    assertEquals(testMerchants, merchantService.findStarterUnavailableMerchants());
    verify(merchantRepository).findAll();
  }

  @Test
  public void pickUpStarterCards_ReturnsBasicMerchants_ThereAreTwoMerchantCards() {
    this.testMerchants.get(0).setId(1L);
    this.testMerchants.get(1).setId(2L);

    when(merchantRepository.findById(1L)).thenReturn(Optional.of(testMerchants.get(0)));
    when(merchantRepository.findById(2L)).thenReturn(Optional.of(testMerchants.get(1)));
    assertEquals(testMerchants, merchantService.pickUpStarterCards());
    verify(merchantRepository).findById(1L);
    verify(merchantRepository).findById(2L);
  }

}
