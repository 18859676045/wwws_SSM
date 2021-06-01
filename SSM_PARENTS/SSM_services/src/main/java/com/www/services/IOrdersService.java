package com.www.services;

import com.www.main.Orders;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IOrdersService {



    List<Orders> findAll(int page, int size) throws Exception;

    Orders findById(String id) throws Exception;
}
