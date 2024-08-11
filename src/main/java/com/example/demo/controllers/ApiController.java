package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.CompletedOrderTable;
import com.example.demo.models.PendingOrderTable;
import com.example.demo.repositrys.CompletedTableRepositry;
import com.example.demo.repositrys.PendingTableRepositry;
import com.example.demo.services.OrderService;

@CrossOrigin("http://localhost:3000/")
@RestController
public class ApiController {
	
	private PendingTableRepositry pendingTableRepositry;
	
	private CompletedTableRepositry completedTableRepositry;
	
	private OrderService orderService;

	

	public ApiController(PendingTableRepositry pendingTableRepositry, CompletedTableRepositry completedTableRepositry,
			OrderService orderService) {
		super();
		this.pendingTableRepositry = pendingTableRepositry;
		this.completedTableRepositry = completedTableRepositry;
		this.orderService = orderService;
	}


	@GetMapping("/data")
	public String getTablesData() {
		
		return "Fetching the tables data...";
	}
	
	@PostMapping("/addPendingTable")
	public void postPendingTableData( @RequestBody PendingOrderTable pot) {
		System.out.println("Hello");
		 orderService.processNewBuyerOrder(pot.getBuyerQty(),pot.getBuyerPrice());
		 
		 
	}
	
	@PostMapping("/addCompletedTable")
	public CompletedOrderTable postCompletedTableData(@RequestBody CompletedOrderTable completeTable) {
		
		
		CompletedOrderTable p=this.completedTableRepositry.save(completeTable);
		
		return p;
	}
	
	@GetMapping("/getPendingAndCompleteQuantity")
	public Map<String,Integer> getQuantity() {
		int pendingQuantity=0;
		int completedQuantity=0;
		List<PendingOrderTable>pendingList=this.pendingTableRepositry.findAll();
		List<CompletedOrderTable>completedList=this.completedTableRepositry.findAll();
		for(PendingOrderTable table:pendingList) {
			pendingQuantity+=table.getSellerQty();
		}
		for(CompletedOrderTable table:completedList) {
			completedQuantity+=table.getQuantity();
		}
		 Map<String,Integer> res=new HashMap<>();
		 res.put("pendingQuantity", pendingQuantity);
		 res.put("completedQuantity",completedQuantity);
		 return res;
	}
	
	@GetMapping("/getPendingData")
	public List<PendingOrderTable> getPendingData() {
		
		return this.pendingTableRepositry.findAll();
	}
	
	@GetMapping("/getCompletedData")
	public List<CompletedOrderTable> getCompletedData() {
		
		return this.completedTableRepositry.findAll();
	}
	
	@GetMapping("/getData")
	public List<PendingOrderTable> checkForMatching() {
		List<PendingOrderTable> list= this.pendingTableRepositry.findAll();
		
		return list;
	}
	
}
