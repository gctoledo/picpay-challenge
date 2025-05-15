package dev.gabrieltoledo.picpaychallenge.adapter;

import org.springframework.stereotype.Component;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import dev.gabrieltoledo.picpaychallenge.domain.user.UserType;

@Component
public class DocumentValidator {

    private final CPFValidator cpfValidator;
    private final CNPJValidator cnpjValidator;

    public DocumentValidator() {
        this.cpfValidator = new CPFValidator();
        this.cnpjValidator = new CNPJValidator();
    }

    public boolean isValid(String document, UserType userType) {
        try {
            if (userType == UserType.PERSONAL) {
                cpfValidator.assertValid(document);
            }

            if (userType == UserType.BUSINESS) {
                cnpjValidator.assertValid(document);
            }
        } catch (InvalidStateException e) {
            return false;
        }

        return true;
    }
}
