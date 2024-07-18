package com.example.fibonacci.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.fibonacci.dto.TimeRequest;
import com.example.fibonacci.model.FibonacciResult;
import com.example.fibonacci.repository.FibonacciRepository;

@RestController
public class FibonacciController {
	@Autowired
	private FibonacciRepository fibonacciRepository;
	
	@PostMapping("/fibonacci")
	public List<Integer> getFibonacciSeries(@RequestBody TimeRequest timeRequest) {
		 String timeString = timeRequest.getTime();
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	     LocalTime time = LocalTime.parse(timeString, formatter);
	     
	     int x = time.getMinute() % 10;
	     int y = time.getMinute() / 10;
	     int n = time.getSecond();

	     List<Integer> fibonacciSeries = generateFibonacciSeries(x, y, n);
	     Collections.reverse(fibonacciSeries);
	     
	     FibonacciResult result = new FibonacciResult();
	     result.setSeries(fibonacciSeries.toString());
	     result.setSeed(timeString);
	     fibonacciRepository.save(result);
	     
	     return fibonacciSeries;
	}
	
	@GetMapping("/fibonacci")
    public List<FibonacciResult> getAllSeeds() {
        return fibonacciRepository.findAll();
    }
	
    private List<Integer> generateFibonacciSeries(int x, int y, int n) {
        List<Integer> series = new ArrayList<>();
        series.add(x);
        series.add(y);

        for (int i = 2; i < n + 2; i++) {
            int next = series.get(i - 1) + series.get(i - 2);
            series.add(next);
        }

        return series;
    }
}