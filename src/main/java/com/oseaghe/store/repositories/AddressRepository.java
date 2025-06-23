package com.oseaghe.store.repositories;

import com.oseaghe.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}