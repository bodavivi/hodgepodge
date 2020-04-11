package com.lavien.hodgepodge.services;

import com.lavien.hodgepodge.models.Mixture;

import java.util.List;

public interface MixtureService {
  List<Mixture> findAll();
  Mixture save(Mixture mixture);
}
