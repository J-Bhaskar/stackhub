package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.CompletedOrderTable;
import com.example.demo.models.PendingOrderTable;
import com.example.demo.repositrys.CompletedTableRepositry;
import com.example.demo.repositrys.PendingTableRepositry;

@Service
public class OrderService {
	
	
	

		@Autowired
	    private PendingTableRepositry pendingOrderRepository;

	    @Autowired
	    private CompletedTableRepositry completedOrderRepository;

	    public void processNewBuyerOrder(int qty, double price) {
	        List<PendingOrderTable> matchingOrders = pendingOrderRepository.findBySellerPriceLessThanEqualOrderBySellerPriceAsc(price);

	        int remainingQty = qty;

	        for (PendingOrderTable order : matchingOrders) {
	            if (remainingQty <= 0) break;

	            if (order.getSellerQty() <= remainingQty) {
	                // Move to CompletedOrder table
	                CompletedOrderTable completedOrder = new CompletedOrderTable();
	                completedOrder.setPrice(order.getSellerPrice());
	                completedOrder.setQuantity(order.getSellerQty());
	                completedOrderRepository.save(completedOrder);

	                // Reduce buyer's remaining quantity
	                remainingQty -= order.getSellerQty();

	                // Remove the order from PendingOrder table
	                pendingOrderRepository.delete(order);
	            } else {
	                // Partially fulfill the order
	                CompletedOrderTable completedOrder = new CompletedOrderTable();
	                completedOrder.setPrice(order.getSellerPrice());
	                completedOrder.setQuantity(remainingQty);
	                completedOrderRepository.save(completedOrder);

	                // Update the seller's order with remaining quantity
	                order.setSellerQty(order.getSellerQty() - remainingQty);
	                pendingOrderRepository.save(order);

	                remainingQty = 0;
	            }
	        }

	        if (remainingQty > 0) {
	            // If there's any remaining quantity, add it to the PendingOrder table as a new buyer order
	            PendingOrderTable newOrder = new PendingOrderTable();
	            newOrder.setBuyerQty(remainingQty);
	            newOrder.setBuyerPrice(price);
	            newOrder.setSellerPrice(price + 1); // A placeholder, adjust as needed
	            newOrder.setSellerQty(10); // A placeholder, adjust as needed
	            pendingOrderRepository.save(newOrder);
	        }
	    }
	
	}


