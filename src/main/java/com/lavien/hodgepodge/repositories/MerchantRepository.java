package com.lavien.hodgepodge.repositories;

import com.lavien.hodgepodge.models.merchants.Merchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant, Long> {

}
