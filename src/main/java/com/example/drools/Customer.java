package com.example.drools;

public class Customer {
    private String name;
    private int purchaseCount;
    private double totalSpent;
    private String membershipLevel;
    private boolean vipStatus;

    public Customer() {}

    public Customer(String name, int purchaseCount, double totalSpent) {
        this.name = name;
        this.purchaseCount = purchaseCount;
        this.totalSpent = totalSpent;
        this.membershipLevel = "Standard";
        this.vipStatus = false;
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

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", purchaseCount=" + purchaseCount +
                ", totalSpent=" + totalSpent +
                ", membershipLevel='" + membershipLevel + '\'' +
                ", vipStatus=" + vipStatus +
                '}';
    }
}