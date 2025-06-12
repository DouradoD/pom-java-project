package com.example.pojos;

/**
 * POJO representing a User for test data.
 */
public class User {
    private String name;
    private String tier;
    private String username;
    private String password;
    private String levelType;
    private int walletPoints;

    // 1. No-arg constructor (required for frameworks like Jackson/Gson)
    public User() {}

    // 2. All-args constructor (for manual instantiation)
    public User(String name, String tier, String username, String password, String levelType, int walletPoints) {
        this.name = name;
        this.tier = tier;
        this.username = username;
        this.password = password;
        this.levelType = levelType;
        this.walletPoints = walletPoints;
    }

    // 3. Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public int getWalletPoints() {
        return walletPoints;
    }

    public void setWalletPoints(int walletPoints) {
        this.walletPoints = walletPoints;
    }

    // 4. Optional: Override toString() for debugging
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", tier='" + tier + '\'' +
                ", username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                ", levelType='" + levelType + '\'' +
                ", walletPoints=" + walletPoints +
                '}';
    }
}