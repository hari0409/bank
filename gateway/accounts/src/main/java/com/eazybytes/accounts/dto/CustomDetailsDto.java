package com.eazybytes.accounts.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomDetailsDto {
    private CardsDto cardsDto;
    private LoansDto loansDto;
    private CustomerDto customerDto;
}
