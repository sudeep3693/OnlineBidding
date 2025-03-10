package xdezo.bidding.onlineBidding.Payment.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

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
    public List<String> getAmount() {
        return Collections.singletonList(amount);
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<String> getFailureUrl() {
        return Collections.singletonList(failureUrl);
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    public List<String> getProductDeliveryCharge() {
        return Collections.singletonList(productDeliveryCharge);
    }

    public void setProductDeliveryCharge(String productDeliveryCharge) {
        this.productDeliveryCharge = productDeliveryCharge;
    }

    public List<String> getProductServiceCharge() {
        return Collections.singletonList(productServiceCharge);
    }

    public void setProductServiceCharge(String productServiceCharge) {
        this.productServiceCharge = productServiceCharge;
    }

    public List<String> getProductCode() {
        return Collections.singletonList(productCode);
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<String> getSignature() {
        return Collections.singletonList(signature);
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public List<String> getSignedFieldNames() {
        return Collections.singletonList(signedFieldNames);
    }

    public void setSignedFieldNames(String signedFieldNames) {
        this.signedFieldNames = signedFieldNames;
    }

    public List<String> getSuccessUrl() {
        return Collections.singletonList(successUrl);
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public List<String> getTaxAmount() {
        return Collections.singletonList(taxAmount);
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public List<String> getTotalAmount() {
        return Collections.singletonList(totalAmount);
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<String> getTransactionUuid() {
        return Collections.singletonList(transactionUuid);
    }

    public void setTransactionUuid(String transactionUuid) {
        this.transactionUuid = transactionUuid;
    }
}
