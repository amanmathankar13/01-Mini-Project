package com.mini_project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.mini_project.contants.AppConstants;
import com.mini_project.entity.Plan;
import com.mini_project.props.AppProperties;
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


    
    private PlanService planService;

    
    private Map<String,String> messages;

    public PlanController(PlanService planService, AppProperties appProperties){
        this.planService = planService;
        this.messages = appProperties.getMessages();
    }



    @GetMapping("/get")
    public ResponseEntity<List<Plan>> getAllPlans() {
        return ResponseEntity.ok(planService.getAllPlans());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPlan(@RequestBody Plan plan) {
        boolean isSaved = planService.createPlan(plan);
        return isSaved ? new ResponseEntity<>(messages.get(AppConstants.PLAN_SAVE_SUCC), HttpStatus.CREATED) : new ResponseEntity<>(messages.get(AppConstants.PLAN_SAVE_FAIL), HttpStatus.CONFLICT);
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
        return isUpdated ? new ResponseEntity<>(messages.get(AppConstants.PLAN_UPDATE_SUCC) , HttpStatus.ACCEPTED):new ResponseEntity<>(messages.get(AppConstants.PLAN_UPDATE_FAIL), HttpStatus.CONFLICT);
        // We can't write string literals directly it is a bad practice, we must have to use contant class for this which contains all the constant things
    }

    @DeleteMapping("/delete/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable("planId") Integer planId){
        boolean isDeleted = planService.deletePlan(planId);
        return isDeleted ? new ResponseEntity<>(messages.get(AppConstants.PLAN_DELETE_SUCC), HttpStatus.OK):new ResponseEntity<>(messages.get(AppConstants.PLAN_DELETE_FAIL), HttpStatus.CONFLICT);
    }

    @PutMapping("/changeStatus/{planId}/{planStatus}")
    public ResponseEntity<String> statusChange(@PathVariable("planId") Integer planId, @PathVariable("planStatus") String planStatus) {
        boolean isChanged = planService.planStatusChange(planId, planStatus);
        return isChanged ? new ResponseEntity<>(messages.get(AppConstants.PLAN_STATUS_CHANGE), HttpStatus.OK):new ResponseEntity<>(messages.get(AppConstants. PLAN_STATUS_CHANGE_FAIL), HttpStatus.CONFLICT);
    }

}
