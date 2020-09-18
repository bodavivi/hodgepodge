package com.lavien.hodgepodge.services;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lavien.hodgepodge.models.merchants.Merchant;
import com.lavien.hodgepodge.models.merchants.ObtainIngrMerchant;
import com.lavien.hodgepodge.models.merchants.TradeIngrMerchant;
import com.lavien.hodgepodge.models.merchants.UpdateIngrMerchant;
import com.lavien.hodgepodge.repositories.MerchantRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MerchantServiceImplTest {

  private MerchantServiceImpl merchantService;
  private MerchantRepository merchantRepository;

  @Before
  public void setUp() throws Exception {
    this.merchantRepository = mock(MerchantRepository.class);
    this.merchantService = new MerchantServiceImpl(merchantRepository);
  }

  @Test
  public void findAll_ReturnsAllPredefinedMerchants_ThereAreTwoMerchants() {
    Merchant one = new ObtainIngrMerchant(1, 1, 1, 1);
    Merchant two = new UpdateIngrMerchant(3);
    List<Merchant> expected = new ArrayList<>(Arrays.asList(one, two));

    when(merchantRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(one, two)));
    assertEquals(expected, merchantService.findAll());
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
    List<Merchant> merchants = List.of(
        new ObtainIngrMerchant(1, 2, 3, 4),
        new TradeIngrMerchant(1, 1, 1, 1, 0, 0, 0, 0),
        new UpdateIngrMerchant(3));

    merchants.get(0).setId(11L);
    merchants.get(1).setId(12L);
    merchants.get(2).setId(13L);

    when(merchantRepository.findAll()).thenReturn(merchants);
    assertEquals(merchants, merchantService.findStarterUnavailableMerchants());
    verify(merchantRepository).findAll();
  }

  @Test
  public void pickUpStarterCards_ReturnsBasicMerchants_ThereAreTwoMerchantCards() {
    List<Merchant> merchants = List.of(
        new ObtainIngrMerchant(2, 0, 0, 0),
        new UpdateIngrMerchant(2));

    merchants.get(0).setId(1L);
    merchants.get(1).setId(2L);

    when(merchantRepository.findById(1L)).thenReturn(Optional.of(merchants.get(0)));
    when(merchantRepository.findById(2L)).thenReturn(Optional.of(merchants.get(1)));
    assertEquals(merchants, merchantService.pickUpStarterCards());
    verify(merchantRepository).findById(1L);
    verify(merchantRepository).findById(2L);
  }

}
