package com.insys.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.insys.model.EnterpriseNode;

@Repository
public interface EnterpriseNodeRepository extends CrudRepository<EnterpriseNode, Integer> {

}
