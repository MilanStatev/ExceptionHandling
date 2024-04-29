package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.DuplicateEntryException;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.utils.ParsingHelpers;
import com.company.oop.cosmetics.utils.ValidationHelper;

import java.util.List;

public class CreateProductCommand implements Command {

    private static final String PRODUCT_CREATED = "Product with name %s was created!";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    private static final String PRODUCT_ALREADY_EXISTS = "Product %s already exist.";

    private final ProductRepository productRepository;

    public CreateProductCommand(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS, "CreateProduct");

        String name = parameters.get(0);
        String brand = parameters.get(1);

        double price;
        try {
            price = Double.parseDouble(parameters.get(2));
        } catch (NumberFormatException ex) {
            throw new InvalidUserInputException("Third parameter should be price (real number).");
        }

        GenderType gender = ParsingHelpers.tryParseEnum(
                parameters.get(3),
                GenderType.class,
                "Forth parameter should be one of Men, Women or Unisex."
        );

        return createProduct(name, brand, price, gender);
    }

    private String createProduct(String name, String brand, double price, GenderType gender) {
        if (productRepository.productExist(name)) {
            throw new DuplicateEntryException(String.format(PRODUCT_ALREADY_EXISTS, name));
        }
        productRepository.createProduct(name, brand, price, gender);

        return String.format(PRODUCT_CREATED, name);
    }

}
