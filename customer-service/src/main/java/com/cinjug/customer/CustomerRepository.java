package com.cinjug.customer;

import org.springframework.data.repository.PagingAndSortingRepository;

interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
}
