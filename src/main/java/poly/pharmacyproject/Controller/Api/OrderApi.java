package poly.pharmacyproject.Controller.Api;

import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import poly.pharmacyproject.Model.Response.OrderResponse;
import poly.pharmacyproject.Service.OrderService;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/order")
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAllOrder(
            @RequestParam("pageCurrent") Integer pageCurrent,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("sortOrder") String sortOrder,
            @RequestParam("sortBy") String sortBy
    ){
        Map<String,Object> result = new HashMap<>();
        Sort sort = Sort.by(new Sort.Order(Objects.equals(sortOrder, "asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        Pageable pageable = PageRequest.of(pageCurrent, pageSize, sort);
        try {
            result.put("success",true);
            result.put("message","get All Order");
            result.put("data",orderService.getAllOrder(pageable));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getById/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getOrderByOrderId(
            @PathVariable("orderId") Integer orderId
    ){
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","get Order By Id");
            result.put("data",orderService.getOrderByOrderId(orderId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getMyOrderByStatusId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getMyOrderByStatusId(
            @PathVariable("userId") Integer userId,
            @RequestParam("pageCurrent") Integer pageCurrent,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("sortOrder") String sortOrder,
            @RequestParam("sortBy") String sortBy,
            @RequestParam(value = "orderStatusId", required = false) Integer orderStatusId
    ){
        Map<String,Object> result = new HashMap<>();
        Sort sort = Sort.by(new Sort.Order(Objects.equals("asc", sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC ,sortBy));
        Pageable pageable = PageRequest.of(pageCurrent, pageSize, sort);
        try {
            result.put("success",true);
            result.put("message","get MyOrder By Status Id");
            result.put("data",orderStatusId == 0 ?  orderService.getOrdersByUserId(pageable, userId)
                    : orderService.getOrdersByOrderStatusId(pageable, orderStatusId, userId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getByOrderStatusIdAndUserId/{userId}/{orderStatusId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getOrderByStatusIdAndUserId(
            @PathVariable("userId") Integer userId,
            @PathVariable("orderStatusId") Integer orderStatusId,
            @RequestParam("pageCurrent") Integer pageCurrent,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("sortOrder") String sortOrder,
            @RequestParam("sortBy") String sortBy
    ){
        Map<String,Object> result = new HashMap<>();
        Sort sort = Sort.by(new Sort.Order(Objects.equals("asc", sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC ,sortBy));
        Pageable pageable = PageRequest.of(pageCurrent, pageSize, sort);
        try {
            result.put("success",true);
            result.put("message","get Order By Id");
            result.put("data",orderService.getOrdersByOrderStatusId(pageable, orderStatusId, userId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/updateOrderStatus/{orderId}/{actionStatus}")
    public ResponseEntity<Object> updateOrderStatus(
            @PathVariable("orderId") Integer orderId,
            @PathVariable("actionStatus") String actionStatus
    ){
        System.out.println(" updateOrderStatus " + actionStatus );
        Map<String,Object> result = new HashMap<>();
        OrderResponse order;
        if (actionStatus.equals("returnRequest")){
            order = orderService.returnRequest(orderId);
        }else if(actionStatus.equals("cancelOrder")){
            order = orderService.cancelOrder(orderId);
        }else if(actionStatus.equals("completeOrder")){
            order = orderService.completeOrder(orderId);
        }else if(actionStatus.equals("cancelReturnRequest")){
            order= orderService.completeOrder(orderId);
        }else if (actionStatus.equals("review")){
            order = orderService.completeOrder(orderId);
        }else {
            return null;
        }
        try {
            result.put("success",true);
            result.put("message","Update Order Status By Action ");
            result.put("data",order);
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("daa", null);
        }
        return ResponseEntity.ok(result);
    }
}
