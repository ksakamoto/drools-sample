package com.example.drools;

public class Customer {
    private String name;
    private int purchaseCount;
    private double totalSpent;
    private String membershipLevel;
    private boolean vipStatus;
    private CustomerRank currentRank;
    private int loyaltyPoints;

    public Customer() {}

    public Customer(String name, int purchaseCount, double totalSpent) {
        this.name = name;
        this.purchaseCount = purchaseCount;
        this.totalSpent = totalSpent;
        this.membershipLevel = "Standard";
        this.vipStatus = false;
        this.currentRank = CustomerRank.BRONZE;
        this.loyaltyPoints = 0;
    }

    public Customer(String name, int purchaseCount, double totalSpent, int loyaltyPoints) {
        this.name = name;
        this.purchaseCount = purchaseCount;
        this.totalSpent = totalSpent;
        this.membershipLevel = "Standard";
        this.vipStatus = false;
        this.currentRank = CustomerRank.BRONZE;
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public boolean isVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(boolean vipStatus) {
        this.vipStatus = vipStatus;
    }

    public CustomerRank getCurrentRank() {
        return currentRank;
    }

    public void setCurrentRank(CustomerRank currentRank) {
        this.currentRank = currentRank;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", purchaseCount=" + purchaseCount +
                ", totalSpent=" + totalSpent +
                ", membershipLevel='" + membershipLevel + '\'' +
                ", vipStatus=" + vipStatus +
                ", currentRank=" + currentRank +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}