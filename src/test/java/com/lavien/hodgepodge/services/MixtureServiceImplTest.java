package com.lavien.hodgepodge.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lavien.hodgepodge.models.Mixture;
import com.lavien.hodgepodge.repositories.MixtureRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MixtureServiceImplTest {

  private MixtureServiceImpl mixtureService;
  private MixtureRepository mixtureRepository;

  @BeforeEach
  public void setUp() {
    this.mixtureRepository = mock(MixtureRepository.class);
    this.mixtureService = new MixtureServiceImpl(mixtureRepository);
  }

  @Test
  public void findAll_ReturnAllPredefinedMixtures_TwoMixtures() {
    Mixture test1 = new Mixture(6, 2, 2, 0, 0);
    Mixture test2 = new Mixture(20, 2, 2, 2, 1);
    List<Mixture> mixtures = new ArrayList<>(Arrays.asList(test1, test2));

    when(this.mixtureRepository.findAll()).thenReturn(mixtures);

    List<Mixture> result = this.mixtureService.findAll();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(mixtures, this.mixtureService.findAll());
    verify(this.mixtureRepository, times(2)).findAll();
  }

  @Test
  public void findAll_ReturnEmptyList_NoMixture() {
    when(this.mixtureRepository.findAll()).thenReturn(new ArrayList<>());
    assertEquals(new ArrayList<>(), this.mixtureService.findAll());
    verify(this.mixtureRepository).findAll();
  }

  @Test
  public void save_ReturnAndSaveTheSavedMixture_OneMixture() {
    Mixture test = new Mixture(6, 2, 2, 0, 0);

    when(this.mixtureRepository.save(test)).thenReturn(test);

    Mixture result = this.mixtureService.save(test);

    assertNotNull(test);
    assertEquals(6, result.getPoint());
    assertEquals(test, this.mixtureService.save(test));
    verify(this.mixtureRepository, times(2)).save(test);
  }


}
