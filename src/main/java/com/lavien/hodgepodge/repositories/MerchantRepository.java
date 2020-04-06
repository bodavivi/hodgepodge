package com.lavien.hodgepodge.repositories;

import com.lavien.hodgepodge.models.merchants.Merchant;
import org.springframework.data.repository.CrudRepository;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {

}
