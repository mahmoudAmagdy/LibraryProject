package com.applc.library.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.applc.library.services.BookService;
import com.applc.library.services.BorrowingRecordService;
import com.applc.library.services.PatronService;
import com.applc.library.services.implementation.BookServiceImpl;
import com.applc.library.services.implementation.BorrowingRecordServiceImpl;
import com.applc.library.services.implementation.PatronServiceImpl;

@Configuration
@EnableTransactionManagement

public class Beans {
    @Bean
    AccessLog accessLog() {
        return new AccessLog();
    }
    
    @Bean
    BookService bookService() {
    	return new BookServiceImpl();
    }
    
    @Bean
    BorrowingRecordService borrowingRecordService() {
    	return new BorrowingRecordServiceImpl();
    }

    @Bean
    PatronService patronService() {
    	return new PatronServiceImpl();
    }
    
    @Bean
    ModelMapper pojoMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }


}
