package com.company.oop.cosmetics.core;

import com.company.oop.cosmetics.commands.*;
import com.company.oop.cosmetics.core.contracts.CommandFactory;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.utils.ParsingHelpers;
import com.company.oop.cosmetics.utils.ValidationHelper;

public class CommandFactoryImpl implements CommandFactory {

    private static final String INVALID_COMMAND = "Command %s is not supported.";

    @Override
    public Command createCommandFromCommandName(String commandTypeValue, ProductRepository productRepository) {

        CommandType commandType = ParsingHelpers
                .tryParseEnum(commandTypeValue, CommandType.class, String.format(INVALID_COMMAND, commandTypeValue));

        switch (commandType) {
            case CREATECATEGORY:
                return new CreateCategoryCommand(productRepository);
            case CREATEPRODUCT:
                return new CreateProductCommand(productRepository);
            case ADDPRODUCTTOCATEGORY:
                return new AddProductToCategoryCommand(productRepository);
            case SHOWCATEGORY:
                return new ShowCategoryCommand(productRepository);
            default:
                throw new InvalidUserInputException(String.format(INVALID_COMMAND, commandTypeValue));
        }
    }

}
