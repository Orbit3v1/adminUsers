package com.app.validator;

import com.app.entity.Nomenclature;
import com.app.entity.Specification;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named("specificationValidator")
@Scope("request")
public class SpecificationValidator extends AbstractValidator<Specification> {

    @Override
    public boolean validate(Specification specification, Object... args) {
        this.entity = specification;
        //gib = args.length > 1 ? (String) args[1] : "";
        return true;//isValidName() & isValidGib();
    }
}
