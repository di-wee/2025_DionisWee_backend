package nus.iss.models;

import java.util.List;

public class CoinChangeRequest {
    private float targetAmount;
    private List<Double> denominations;

    public CoinChangeRequest() {
    }

    public float getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(float targetAmount) {
        this.targetAmount = targetAmount;
    }

    public List<Double> getDenominations() {
        return denominations;
    }

    public void setDenominations(List<Double> denominations) {
        this.denominations = denominations;
    }
}
