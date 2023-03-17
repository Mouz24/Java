package com.example.api;

import com.example.api.Repository.INumberRepository;
import com.example.api.Models.*;
import com.example.api.Service.NumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

@RestController
@SpringBootApplication
public class NumberController {
    private static Logger log = Logger.getLogger(NumberController.class.getName());

    Lock lock = new ReentrantLock();

    @Autowired
    private Counter _counter;

    @Autowired
    private NumberRepository _repository;

    @Autowired
    private INumberRepository _numberRepository;

    @GetMapping("/number")
    public ResponseEntity<?> GetConvertedNumber(@ModelAttribute InitialNumber number) {
        lock.lock();
        _counter.Increment();
        lock.unlock();

        var result = _repository.Convert(number);

        var check = _numberRepository.findAll().stream().filter(numberEntity -> numberEntity.getInitialValue().equals(number.getValue()));

        //var check = _numberRepository.FindByInitialValue(number.value);
        if (check == null)
        {
            _numberRepository.save(new NumberEntity(number.value, result));
        }

        return new ResponseEntity<>(new ConvertedNumber(result), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<?> GetCount()
    {
        return new ResponseEntity<>(_counter.GetCounter(), HttpStatus.OK);
    }

    @PostMapping("/number")
    public ResponseEntity<?> Bulk(@RequestBody Collection<InitialNumber> numbers)
    {
        for (var entity : numbers) {
            var check = _numberRepository.findAll().stream().filter(numberEntity -> numberEntity.getInitialValue().equals(entity.getValue()));

            if (check.findAny().isEmpty())
            {
                _numberRepository.save(new NumberEntity(entity.value, _repository.Convert(entity)));
            }
        }

        return new ResponseEntity<>(_repository.Convert(numbers), HttpStatus.OK);
    }

    @GetMapping("/numberAsync")
    public ResponseEntity<?> GetConvertedNumberAsync(@ModelAttribute InitialNumber number){
        var entity = new NumberEntity(number.getValue(), null);

        _numberRepository.save(entity);

        _repository.ConvertAsync(entity);

        return new ResponseEntity<>(entity.getId(), HttpStatus.OK);
    }

    @GetMapping("/numberById")
    public ResponseEntity<?> GetConvertedNumberById(@RequestParam Long id) {

        var result = _repository.Result(id);

        return new ResponseEntity<>(new ConvertedNumber(result), HttpStatus.OK);
    }
}