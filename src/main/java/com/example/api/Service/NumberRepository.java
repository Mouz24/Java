package com.example.api.Service;

import com.example.api.Repository.INumberRepository;
import com.example.api.Models.*;
import com.example.api.NumberController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component(value="numberRepository")
@Scope("singleton")
public class NumberRepository
{
    private static Logger log = Logger.getLogger(NumberController.class.getName());

    @Autowired
    private Cache _cache;

    @Autowired
    private INumberRepository _numberRepository;

    public Integer Convert(InitialNumber number)
    {
        if (number.value == null)
        {
            log.info("The number is null");

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ValidationErrorConstants.BadRequest);
        }

        try
        {
            if (_cache.Get(number.value) == null)
            {
                var result = Integer.parseInt(Integer.toBinaryString(number.value));

                _cache.Put(number.value, result);
            }

            return _cache.Get(number.value);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ValidationErrorConstants.ServerError);
        }
    }
    public CompletableFuture<Integer> ConvertAsync(NumberEntity number)
    {
        try {
            return CompletableFuture.supplyAsync(() ->
            {
                if (_cache.Get(number.getInitialValue()) == null) {
                    var result = Convert(new InitialNumber(number.getInitialValue()));

                    number.setConvertedValue(result);

                    _numberRepository.save(number);

                    _cache.Put(number.getInitialValue(), result);
                }

                return _cache.Get(number.getInitialValue());
            });
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ValidationErrorConstants.ServerError);
        }
    }
    
    public InputParamsStats Convert(Collection<InitialNumber> numbers)
    {
        try
        {
            var ConvertedNumbersList = numbers.stream().map(number -> Convert(number))
                    .map(ConvertedNumber::new).collect(Collectors.toList());

            var Stats = new InputParamsStats();

            Stats.SetInputStats(numbers);

            Stats.SetResultStats(ConvertedNumbersList);

            return Stats;
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ValidationErrorConstants.ServerError);
        }
    }

    public Integer Result(Long entityId)
    {
        var e = _numberRepository.findById(entityId);

        return e.get().getConvertedValue();
    }
}

//Integer.valueOf(Integer.toBinaryString(number.value))