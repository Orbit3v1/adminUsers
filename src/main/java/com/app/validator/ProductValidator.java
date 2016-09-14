package com.app.validator;

import com.app.data.entity.Order;
import com.app.data.entity.Product;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named("productValidator")
@Scope("request")
public class ProductValidator extends AbstractValidator<Product>{
    @Override
    public boolean validate(Product entity, Object... args) {
        return true;
    }
}
