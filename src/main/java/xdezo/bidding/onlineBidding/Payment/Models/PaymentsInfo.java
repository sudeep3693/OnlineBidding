package xdezo.bidding.onlineBidding.Payment.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentsInfo {

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("failure_url")
    private String failureUrl;

    @JsonProperty("product_delivery_charge")
    private String productDeliveryCharge;

    @JsonProperty("product_service_charge")
    private String productServiceCharge;

    @JsonProperty("product_code")
    private String productCode;

    @JsonProperty("signature")
    private String signature;

    @JsonProperty("signed_field_names")
    private String signedFieldNames;

    @JsonProperty("success_url")
    private String successUrl;

    @JsonProperty("tax_amount")
    private String taxAmount;

    @JsonProperty("total_amount")
    private String totalAmount;

    @JsonProperty("transaction_uuid")
    private String transactionUuid;

    // Getters and Setters
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    public String getProductDeliveryCharge() {
        return productDeliveryCharge;
    }

    public void setProductDeliveryCharge(String productDeliveryCharge) {
        this.productDeliveryCharge = productDeliveryCharge;
    }

    public String getProductServiceCharge() {
        return productServiceCharge;
    }

    public void setProductServiceCharge(String productServiceCharge) {
        this.productServiceCharge = productServiceCharge;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignedFieldNames() {
        return signedFieldNames;
    }

    public void setSignedFieldNames(String signedFieldNames) {
        this.signedFieldNames = signedFieldNames;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransactionUuid() {
        return transactionUuid;
    }

    public void setTransactionUuid(String transactionUuid) {
        this.transactionUuid = transactionUuid;
    }
}
