package com.bank.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
    @Pattern(regexp = "(^$[0-9]{10})", message = "Account Number must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account Type cannot be Null or Empty.")
    private String accountType;

    @NotEmpty(message = "Account Type cannot be Null or Empty.")
    private String branchAddress;

}
