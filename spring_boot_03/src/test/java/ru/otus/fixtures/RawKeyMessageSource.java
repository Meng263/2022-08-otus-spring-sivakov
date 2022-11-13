package ru.otus.fixtures;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

import java.util.Arrays;
import java.util.Locale;

public class RawKeyMessageSource implements MessageSource {

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return code;
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return code;
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return Arrays.stream(resolvable.getCodes()).findFirst().orElse(resolvable.getDefaultMessage());
    }
}
