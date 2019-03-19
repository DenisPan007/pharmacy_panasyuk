package by.panasyuk.service;

import by.panasyuk.controller.command.CommandException;
import by.panasyuk.domain.Item;
import by.panasyuk.domain.Order;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.RepositoryFactory;
import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.impl.JdbcRepositoryFactory;
import by.panasyuk.repository.impl.ItemRepository;
import by.panasyuk.repository.impl.OrderRepository;
import by.panasyuk.repository.impl.TransactionManager;
import by.panasyuk.repository.specification.Specification;
import by.panasyuk.repository.specification.order.GetAllOrders;
import by.panasyuk.repository.specification.order.GetOrderById;
import by.panasyuk.repository.specification.order.GetOrdersByUserId;
import by.panasyuk.service.exception.ServiceException;

import java.util.List;

public class OrderService {
    private RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    private Repository<Order, Integer> orderRepository = repositoryFactory.getRepository(OrderRepository::new);
    private Repository<Order, Integer> orderTransactionalRepository = repositoryFactory.getTransactionalRepository(OrderRepository::new);
    private Repository<Item, Integer> itemTransactionalRepository = repositoryFactory.getTransactionalRepository(ItemRepository::new);

    public Order getOrderById(int id) throws ServiceException {
        Order order = new Order();
        order.setId(id);
        List<Order> orderList = null;
        try {
            orderList = orderRepository.getQuery(order,new GetOrderById());
            if(orderList.isEmpty()){
                return null;
            }
            return orderList.get(0);
        } catch (RepositoryException e) {
            throw  new ServiceException(e);
        }
    }
    public Order addOrder(Order order) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        List<Item> itemList = order.getItemList();
        try {
            manager.begin(itemTransactionalRepository, orderTransactionalRepository);
            Order resultOrder = orderTransactionalRepository.add(order);
            for (Item item : itemList) {
                item.setOrderId(resultOrder.getId());
                itemTransactionalRepository.add(item);
            }
            manager.commit();
            return order;


        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    public void pay(Order order) throws ServiceException {

        try {
            order.setStatus(Order.Status.AT_WORK.name());
            orderRepository.update(order);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    public List<Order> getAllUserOrders(int userId) throws ServiceException {
        Specification<Order> spec = new GetOrdersByUserId();
        try {
            Order order = new Order();
            order.setUserId(userId);
           return orderRepository.getQuery(order,spec);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
