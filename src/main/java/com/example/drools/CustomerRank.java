package com.example.drools;

public enum CustomerRank {
    BRONZE("Bronze", 0, 0, 0),
    SILVER("Silver", 10, 5000.0, 100),
    GOLD("Gold", 25, 15000.0, 300),
    PLATINUM("Platinum", 50, 50000.0, 1000);

    private final String displayName;
    private final int minPurchaseCount;
    private final double minTotalSpent;
    private final int minLoyaltyPoints;

    CustomerRank(String displayName, int minPurchaseCount, double minTotalSpent, int minLoyaltyPoints) {
        this.displayName = displayName;
        this.minPurchaseCount = minPurchaseCount;
        this.minTotalSpent = minTotalSpent;
        this.minLoyaltyPoints = minLoyaltyPoints;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getMinPurchaseCount() {
        return minPurchaseCount;
    }

    public double getMinTotalSpent() {
        return minTotalSpent;
    }

    public int getMinLoyaltyPoints() {
        return minLoyaltyPoints;
    }

    public CustomerRank getNextRank() {
        CustomerRank[] ranks = CustomerRank.values();
        int currentIndex = this.ordinal();
        if (currentIndex < ranks.length - 1) {
            return ranks[currentIndex + 1];
        }
        return null;
    }
}