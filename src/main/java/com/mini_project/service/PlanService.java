package com.mini_project.service;

import java.util.List;
import java.util.Map;
import com.mini_project.entity.Plan;

public interface PlanService {
    public List<Plan> getAllPlans();
    public Plan getPlanById(Integer planId);
    public boolean createPlan(Plan plan);
    public boolean updatePlan(Plan plan);
    public boolean deletePlan(Integer planId);
    public boolean planStatusChange(Integer planId, String status);
    public Map<Integer, String> getPlanCategories();
   



}
