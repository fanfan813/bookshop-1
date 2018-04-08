package com.book.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.dao.OrderMapper;
import com.book.dao.OrderitemMapper;
import com.book.domain.Order;
import com.book.domain.Orderitem;
import com.book.domain.User;
import com.book.service.AddressService;
import com.book.service.BookService;
import com.book.service.CartService;
import com.book.service.OrderService;
import com.book.util.Pager;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderitemMapper orderitemMapper;
	@Autowired
	private BookService bookService;
	@Autowired
	private CartService cartService;
	@Autowired
	private AddressService addressService;
	@Override
	@Transactional
	public void save(Order order) {
		if(StringUtils.isEmpty(order.getOid())){
			User user=(User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			if(user !=null){
				order.setUid(user.getUsername());
			}else{
				return;
			}
			order.setState("0");
			order.setOid(UUID.randomUUID().toString().replace("-", ""));
			orderMapper.insert(order);
			List<Orderitem> orderitems=order.getOrderitems();
			if(orderitems!=null){
				for(Orderitem orderitem:orderitems){
					if(orderitem !=null){
						orderitem.setIid(UUID.randomUUID().toString().replace("-", ""));
						orderitem.setOid(order.getOid());
						orderitemMapper.insert(orderitem);
					}
				}
			}
			cartService.removeByUId(user.getUsername());
		}
		
	}
	@Override
	public Pager query(Order order, String orderBy,String entitySQL, int pageNum, int pageSize) {
		Map<String,Object> params=new HashMap<String, Object>();
		Pager pager=new Pager(pageNum, pageSize);
		RowBounds rowBounds=new RowBounds((pageNum-1)*pageSize, pageSize);
		params.put("dto", order);
		params.put("orderBy", orderBy);
		params.put("entitySQL", entitySQL);
		List<Order> list= orderMapper.query(params,rowBounds);
		Integer count=orderMapper.queryCount(params);
		pager.setRows(list);
		pager.setRecords(count);
		return pager;
	}
	@Override
	public Order get(String id) {
		Order order=orderMapper.findById(id);
		List<Orderitem> items=orderitemMapper.findByOid(order.getOid());
		for(Orderitem item:items){
			item.setBook(bookService.findById(item.getBid()));
		}
		order.setOrderitems(items);
		order.setAddressInfo(addressService.findById(order.getAddress()));
		return order;
	}
	@Override
	public void edit(Order order) {
		orderMapper.update(order);
		
	}
	@Override
	@Transactional
	public void remove(String oid) {
		Order order=orderMapper.findById(oid);
		if(order !=null){
			orderitemMapper.removeByoId(oid);
			orderMapper.remove(oid);
		}
		
	}

}
