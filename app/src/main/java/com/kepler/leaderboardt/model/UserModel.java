package com.kepler.leaderboardt.model;

import java.util.Objects;

public class UserModel {
    private final int imageResource;
    private final String name;
    private final int points;
    private final int amount;
    private final int playerRank;

    public UserModel(int imageResource, String name, int points, int amount, int playerRank) {
        this.imageResource = imageResource;
        this.name = name;
        this.points = points;
        this.amount = amount;
        this.playerRank = playerRank;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getAmount() {
        return amount;
    }

    public int getPlayerRank() {
        return playerRank;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UserModel userModel = (UserModel) obj;

        if (imageResource != userModel.imageResource) return false;
        if (points != userModel.points) return false;
        if (playerRank != userModel.playerRank) return false;
        if (Double.compare(userModel.amount, amount) != 0) return false;
        return Objects.equals(name, userModel.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = imageResource;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + points;
        result = 31 * result + playerRank;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
