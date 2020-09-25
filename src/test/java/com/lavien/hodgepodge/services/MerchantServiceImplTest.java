package com.lavien.hodgepodge.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lavien.hodgepodge.models.merchants.Merchant;
import com.lavien.hodgepodge.models.merchants.ObtainIngrMerchant;
import com.lavien.hodgepodge.models.merchants.UpdateIngrMerchant;
import com.lavien.hodgepodge.repositories.MerchantRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MerchantServiceImplTest {

  private MerchantServiceImpl merchantService;
  private MerchantRepository merchantRepository;
  private List<Merchant> merchants;

  @BeforeEach
  public void setUp() {
    this.merchantRepository = mock(MerchantRepository.class);
    this.merchantService = new MerchantServiceImpl(merchantRepository);
    Merchant test1 = new ObtainIngrMerchant(2, 0, 0, 0);
    Merchant test2 = new UpdateIngrMerchant(2);
    this.merchants = new ArrayList<>(Arrays.asList(test1, test2));
  }

  @Test
  public void findAll_ReturnAllPredefinedMerchants_TwoMerchants() {
    when(this.merchantRepository.findAll()).thenReturn(this.merchants);

    List<Merchant> result = this.merchantService.findAll();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(this.merchants, this.merchantService.findAll());
    verify(this.merchantRepository, times(2)).findAll();
  }

  @Test
  public void findAll_ReturnEmptyList_NoMerchant() {
    when(this.merchantRepository.findAll()).thenReturn(new ArrayList<>());
    assertEquals(new ArrayList<>(), this.merchantService.findAll());
    verify(this.merchantRepository).findAll();
  }

  @Test
  public void save_ReturnAndSaveTheSavedMerchant_OneMerchant() {
    Merchant test = new UpdateIngrMerchant(7);

    when(this.merchantRepository.save(test)).thenReturn(test);

    UpdateIngrMerchant result = (UpdateIngrMerchant) this.merchantService.save(test);

    assertNotNull(result);
    assertEquals(7, result.getNumberOfUpdates());
    assertEquals(test, this.merchantService.save(test));
    verify(this.merchantRepository, times(2)).save(test);
  }

  @Test
  public void findStarterUnavailableMerchants_ReturnAllMerchantsExceptStarters_TwoMerchants() {
    this.merchants.get(0).setId(100L);
    this.merchants.get(1).setId(200L);

    when(this.merchantRepository.findAll()).thenReturn(this.merchants);

    List<Merchant> result = this.merchantService.findStarterUnavailableMerchants();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(this.merchants, this.merchantService.findStarterUnavailableMerchants());
    verify(merchantRepository, times(2)).findAll();
  }

  @Test
  public void findStarterUnavailableMerchants_ReturnEmptyList_NoMerchant() {
    when(this.merchantRepository.findAll()).thenReturn(new ArrayList<>());
    assertEquals(new ArrayList<>(), this.merchantService.findStarterUnavailableMerchants());
    verify(this.merchantRepository).findAll();
  }

  @Test
  public void pickUpStarterCards_ReturnStarterCards_TwoMerchants() {
    this.merchants.get(0).setId(1L);
    this.merchants.get(1).setId(2L);

    when(this.merchantRepository.findById(1L)).thenReturn(Optional.of(this.merchants.get(0)));
    when(this.merchantRepository.findById(2L)).thenReturn(Optional.of(this.merchants.get(1)));

    List<Merchant> result = this.merchantService.pickUpStarterCards();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(1L, result.get(0).getId());
    assertEquals(2L, result.get(1).getId());
    assertEquals(merchants, this.merchantService.pickUpStarterCards());
    verify(merchantRepository, times(2)).findById(1L);
    verify(merchantRepository, times(2)).findById(2L);
  }

}
