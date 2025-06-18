package com.mini_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini_project.entity.Plan;

@Repository
public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
