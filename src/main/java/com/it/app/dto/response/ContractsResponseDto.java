package com.it.app.dto.response;

/**
 * Response Data Transfer Contracts entity to  Object class
 */

public class ContractsResponseDto {

    private Long id;

    private String data;

    private RealtorResponseDto realtor;

    private FlatResponseDto flat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RealtorResponseDto getRealtor() {
        return realtor;
    }

    public void setRealtor(RealtorResponseDto realtor) {
        this.realtor = realtor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public FlatResponseDto getFlat() {
        return flat;
    }

    public void setFlat(FlatResponseDto flat) {
        this.flat = flat;
    }

}
