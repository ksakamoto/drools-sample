package com.example.drools;

import java.util.ArrayList;
import java.util.List;

public class RankRequirement {
    private String customerName;
    private CustomerRank currentRank;
    private CustomerRank targetRank;
    private List<String> missingRequirements;
    private boolean analysisRequested;
    private boolean analysisCompleted;

    public RankRequirement() {
        this.missingRequirements = new ArrayList<>();
        this.analysisRequested = false;
        this.analysisCompleted = false;
    }

    public RankRequirement(String customerName, CustomerRank currentRank, CustomerRank targetRank) {
        this.customerName = customerName;
        this.currentRank = currentRank;
        this.targetRank = targetRank;
        this.missingRequirements = new ArrayList<>();
        this.analysisRequested = true;
        this.analysisCompleted = false;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public CustomerRank getCurrentRank() {
        return currentRank;
    }

    public void setCurrentRank(CustomerRank currentRank) {
        this.currentRank = currentRank;
    }

    public CustomerRank getTargetRank() {
        return targetRank;
    }

    public void setTargetRank(CustomerRank targetRank) {
        this.targetRank = targetRank;
    }

    public List<String> getMissingRequirements() {
        return missingRequirements;
    }

    public void setMissingRequirements(List<String> missingRequirements) {
        this.missingRequirements = missingRequirements;
    }

    public void addMissingRequirement(String requirement) {
        this.missingRequirements.add(requirement);
    }

    public boolean isAnalysisRequested() {
        return analysisRequested;
    }

    public void setAnalysisRequested(boolean analysisRequested) {
        this.analysisRequested = analysisRequested;
    }

    public boolean isAnalysisCompleted() {
        return analysisCompleted;
    }

    public void setAnalysisCompleted(boolean analysisCompleted) {
        this.analysisCompleted = analysisCompleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RankRequirement{");
        sb.append("customerName='").append(customerName).append('\'');
        sb.append(", currentRank=").append(currentRank);
        sb.append(", targetRank=").append(targetRank);
        sb.append(", analysisCompleted=").append(analysisCompleted);
        sb.append(", missingRequirements=").append(missingRequirements);
        sb.append('}');
        return sb.toString();
    }
}