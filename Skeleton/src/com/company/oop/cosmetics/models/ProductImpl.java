package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.exceptions.NumberNotPositiveException;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.utils.ValidationHelper;

public class ProductImpl implements Product {

    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 10;
    private static final int BRAND_MIN_LENGTH = 2;
    private static final int BRAND_MAX_LENGTH = 10;
    private static final String PRICE_IS_NEGATIVE = "Price can't be negative.";

    private String name;
    private String brand;
    private double price;
    private final GenderType gender;

    public ProductImpl(String name, String brand, double price, GenderType gender) {
        setName(name);
        setBrand(brand);
        setPrice(price);
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        ValidationHelper.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, "Product name");
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        ValidationHelper.validateStringLength(brand, BRAND_MIN_LENGTH, BRAND_MAX_LENGTH, "Product brand");
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        if (price < 0) {
            throw new NumberNotPositiveException(PRICE_IS_NEGATIVE);
        }
        this.price = price;
    }

    public GenderType getGender() {
        return gender;
    }

    @Override
    public String print() {
        return String.format(
                "#%s %s%n" +
                        " #Price: $%.2f%n" +
                        " #Gender: %s%n",
                name,
                brand,
                price,
                gender);
    }

}
