package com.lavien.hodgepodge.services;

import com.lavien.hodgepodge.models.Mixture;
import com.lavien.hodgepodge.repositories.MixtureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MixtureServiceImpl implements MixtureService {
  private MixtureRepository mixtureRepository;

  @Autowired
  public MixtureServiceImpl(MixtureRepository mixtureRepository) {
    this.mixtureRepository = mixtureRepository;
  }

  @Override
  public List<Mixture> findAll(){
    return (List<Mixture>) mixtureRepository.findAll();
  }

  @Override
  public Mixture save(Mixture mixture) {
    return mixtureRepository.save(mixture);
  }
}
