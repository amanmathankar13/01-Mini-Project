package com.mini_project.service.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini_project.entity.Plan;
import com.mini_project.entity.PlanCategory;
import com.mini_project.repository.PlanCategoryRepo;
import com.mini_project.repository.PlanRepo;
import com.mini_project.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService{

    @Autowired
    private PlanRepo planRepo;

    @Autowired
    private PlanCategoryRepo planCategoryRepo;

    @Override
    public List<Plan> getAllPlans() {
        return planRepo.findAll();
    }

    @Override
    public Plan getPlanById(Integer planId) {
        return planRepo.findById(planId).orElse(null);
    }

    @Override
    public boolean createPlan(Plan plan) {
        Plan saved = planRepo.save(plan);
        return saved.getPlanId()!=null;
    }

    @Override
    public boolean updatePlan(Plan plan) {
        return planRepo.save(plan).getPlanId()!=null; // Upsert 
    }

    @Override
    public boolean deletePlan(Integer planId) {
        boolean isDeleted = false;
        try {
            planRepo.deleteById(planId);
            isDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    @Override
    public boolean planStatusChange(Integer planId, String status) {
        Plan plan = getPlanById(planId);
        if(plan!=null){
            plan.setActive_sw(status);
            return updatePlan(plan);
        }
        return false;
    }

    @Override
    public Map<Integer, String> getPlanCategories() {
        List<PlanCategory> categories = planCategoryRepo.findAll();
        Map<Integer, String> categroyMap = new HashMap<>();
        categories.forEach(category->{
            categroyMap.put(category.getCategoryId(), category.getCategoryName());
        });
        return categroyMap;
    }
    
}
