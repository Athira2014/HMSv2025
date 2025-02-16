package com.athira.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.athira.demo.entity.Service;

@Repository
public interface IServiceDao extends JpaRepository<Service, Integer> {

}
