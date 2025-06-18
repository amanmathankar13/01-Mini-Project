package com.mini_project.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.mini_project.entity.Plan;
import com.mini_project.service.PlanService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/plans")
@Tag(name = "PlanController", description = "Plan API")
public class PlanController {


    @Autowired
    private PlanService planService;

    
    @GetMapping("/get")
    public ResponseEntity<List<Plan>> getAllPlans() {
        return ResponseEntity.ok(planService.getAllPlans());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPlan(@RequestBody Plan plan) {
        boolean isSaved = planService.createPlan(plan);
        return isSaved ? new ResponseEntity<>("Plan Created", HttpStatus.CREATED) : new ResponseEntity<>("Plan Creation failed", HttpStatus.CONFLICT);
    }

    @GetMapping("/get/{planId}")
    public ResponseEntity<Plan> getPlanById(@PathVariable("planId") Integer planId) {
        return ResponseEntity.ok(planService.getPlanById(planId));
    }

    @GetMapping("/categories")
    public ResponseEntity<Map<Integer, String>> getPlanCategories() {
        return ResponseEntity.ok(planService.getPlanCategories());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
        boolean isUpdated = planService.updatePlan(plan);
        return isUpdated ? new ResponseEntity<>("Plan Updated" , HttpStatus.ACCEPTED):new ResponseEntity<>("Failed to Update", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/delete/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable("planId") Integer planId){
        boolean isDeleted = planService.deletePlan(planId);
        return isDeleted ? new ResponseEntity<>("Plan Deleted", HttpStatus.OK):new ResponseEntity<>("Failed to Delete", HttpStatus.CONFLICT);
    }

    @PutMapping("/changeStatus/{planId}/{planStatus}")
    public ResponseEntity<String> statusChange(@PathVariable("planId") Integer planId, @PathVariable("planStatus") String planStatus) {
        boolean isChanged = planService.planStatusChange(planId, planStatus);
        return isChanged ? new ResponseEntity<>("Status Changed", HttpStatus.OK):new ResponseEntity<>("Failed to change status", HttpStatus.CONFLICT);
    }

}
