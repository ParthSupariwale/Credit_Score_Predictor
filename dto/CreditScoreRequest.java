package com.fintech.creditscoring.dto;

import jakarta.validation.constraints.*;

public class CreditScoreRequest {
    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be at most 100")
    private Integer age;

    private Double annualIncome;
    private Double monthlyInhandSalary;
    private Integer creditHistoryAge;
    private Double totalEmiPerMonth;
    private Integer numBankAccounts;
    private Integer numCreditCard;
    private Double interestRate;
    private Integer numLoan;
    private Integer delayFromDueDate;
    private Integer numDelayedPayment;
    private Double changedCreditLimit;
    private Integer numCreditInquiries;
    private String creditMix;
    private Double outstandingDebt;
    private Double creditUtilizationRatio;
    private String paymentOfMinAmount;
    private Double amountInvestedMonthly;
    private String paymentBehaviour;
    private Double monthlyBalance;
    private String typeOfLoan;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public Double getMonthlyInhandSalary() {
        return monthlyInhandSalary;
    }

    public void setMonthlyInhandSalary(Double monthlyInhandSalary) {
        this.monthlyInhandSalary = monthlyInhandSalary;
    }
    public Integer getCreditHistoryAge() {
        return creditHistoryAge;
    }
    public void setCreditHistoryAge(Integer creditHistoryAge) {
        this.creditHistoryAge = creditHistoryAge;
    }
    public Double getTotalEmiPerMonth() {
        return totalEmiPerMonth;
    }
    public void setTotalEmiPerMonth(Double totalEmiPerMonth) {
        this.totalEmiPerMonth = totalEmiPerMonth;
    }
    public Integer getNumBankAccounts() {
        return numBankAccounts;
    }
    public void setNumBankAccounts(Integer numBankAccounts) {
        this.numBankAccounts = numBankAccounts;
    }
    public Integer getNumCreditCard() {
        return numCreditCard;
    }
    public void setNumCreditCard(Integer numCreditCard) {
        this.numCreditCard = numCreditCard;
    }
    public Double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
    public Integer getNumLoan() {
        return numLoan;
    }
    public void setNumLoan(Integer numLoan) {
        this.numLoan = numLoan;
    }
    public Integer getDelayFromDueDate() {
        return delayFromDueDate;
    }
    public void setDelayFromDueDate(Integer delayFromDueDate) {
        this.delayFromDueDate = delayFromDueDate;
    }
    public Double getChangedCreditLimit() {
        return changedCreditLimit;
    }
    public void setChangedCreditLimit(Double changedCreditLimit) {
        this.changedCreditLimit = changedCreditLimit;
    }
    public Integer getNumCreditInquiries() {
        return numCreditInquiries;
    }
    public void setNumCreditInquiries(Integer numCreditInquiries) {
        this.numCreditInquiries = numCreditInquiries;
    }
    public String getCreditMix() {
        return creditMix;
    }
    public void setCreditMix(String creditMix) {
        this.creditMix = creditMix;
    }
    public Double getOutstandingDebt() {
        return outstandingDebt;
    }
    public void setOutstandingDebt(Double outstandingDebt) {
        this.outstandingDebt = outstandingDebt;
    }
    public Double getCreditUtilizationRatio() {
        return creditUtilizationRatio;
    }
    public void setCreditUtilizationRatio(Double creditUtilizationRatio) {
        this.creditUtilizationRatio = creditUtilizationRatio;
    }
    public String getPaymentOfMinAmount() {
        return paymentOfMinAmount;
    }
    public void setPaymentOfMinAmount(String paymentOfMinAmount) {
        this.paymentOfMinAmount = paymentOfMinAmount;
    }
    public Double getAmountInvestedMonthly() {
        return amountInvestedMonthly;
    }
    public void setAmountInvestedMonthly(Double amountInvestedMonthly) {
        this.amountInvestedMonthly = amountInvestedMonthly;
    }
    public String getPaymentBehaviour() {
        return paymentBehaviour;
    }
    public void setPaymentBehaviour(String paymentBehaviour) {
        this.paymentBehaviour = paymentBehaviour;
    }
    public Double getMonthlyBalance() {
        return monthlyBalance;
    }
    public void setMonthlyBalance(Double monthlyBalance) {
        this.monthlyBalance = monthlyBalance;
    }
    public String getTypeOfLoan() {
        return typeOfLoan;
    }
    public void setTypeOfLoan(String typeOfLoan) {
        this.typeOfLoan = typeOfLoan;
    }

    public Integer getNumDelayedPayment() {
        return numDelayedPayment;
    }
    public void setNumDelayedPayment(Integer numDelayedPayment) {
        this.numDelayedPayment = numDelayedPayment;
    }
}