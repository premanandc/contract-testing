package com.bcus.customer;

import org.springframework.data.repository.PagingAndSortingRepository;

interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
}
