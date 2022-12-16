/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import model.entities.Order;
import model.entities.OrderExecution;
import model.entities.RelatesAccountAsset;
import model.enums.StateOrder;
import model.enums.TypeOrder;
import model.repositories.impl.OrderImpl;
import model.repositories.services.OrderServices;

/**
 *
 * @author pedro
 */
public class OrderController {

    private static final OrderImpl database = new OrderImpl();

    private static final OrderServices databaseServices = new OrderServices();

    public static Order search(Long id) {
        return databaseServices.target(id);
    }

    public static boolean verifyOrderExecution(Order attempt) {
        if (attempt.getType().equals(TypeOrder.BUY)) {
            Order satisfy = databaseServices.verifyOrderBuy(attempt);
            if (satisfy != null) {

                BigDecimal value = null;
                Integer quantity = null;

                OrderExecution executionBuy = new OrderExecution();
                OrderExecution executionSeller = new OrderExecution();

                executionBuy.setOrder(attempt.getId());
                executionSeller.setOrder(satisfy.getId());

                executionBuy.setBuyer(attempt.getAccount());
                executionSeller.setBuyer(attempt.getAccount());

                executionBuy.setSeller(satisfy.getAccount());
                executionSeller.setSeller(satisfy.getAccount());

                executionBuy.setStart(LocalDateTime.now());
                executionSeller.setStart(LocalDateTime.now());

                if (Objects.equals(satisfy.getQuantity(), attempt.getQuantity())) {

                    value = satisfy.getTotalValue();
                    quantity = satisfy.getQuantity();

                    executionBuy.setQuantity(attempt.getQuantity());
                    executionSeller.setQuantity(satisfy.getQuantity());

                    databaseServices.updateState(attempt.getId(), StateOrder.TOTAL);
                    databaseServices.updateState(satisfy.getId(), StateOrder.TOTAL);

                    // gerenciar relacionamentos das contas com os ativos
                    // atualizar o preço do ativo
                } else if (satisfy.getQuantity() < attempt.getQuantity()) {

                    value = satisfy.getTotalValue();
                    quantity = satisfy.getQuantity();

                    executionBuy.setQuantity(satisfy.getQuantity());
                    executionSeller.setQuantity(satisfy.getQuantity());

                    databaseServices.updateState(attempt.getId(), StateOrder.PARCIAL);
                    databaseServices.updateState(satisfy.getId(), StateOrder.TOTAL);

                } else if (satisfy.getQuantity() > attempt.getQuantity()) {

                    value = attempt.getTotalValue();
                    quantity = attempt.getQuantity();

                    executionBuy.setQuantity(attempt.getQuantity());
                    executionSeller.setQuantity(attempt.getQuantity());

                    databaseServices.updateState(attempt.getId(), StateOrder.TOTAL);
                    databaseServices.updateState(satisfy.getId(), StateOrder.PARCIAL);

                }

                Long idRelatesBuyer = RelatesController.requestId(attempt.getAccount(), attempt.getAsset());
                Long idRelatesSeller = RelatesController.requestId(satisfy.getAccount(), attempt.getAsset());

                if (idRelatesBuyer != null) {
                    if (RelatesController.addAmount(idRelatesBuyer, quantity)
                            && OrderExecutionController.create(executionBuy)
                            && OrderExecutionController.create(executionSeller)
                            && AccountController.transfer(satisfy.getAccount(), value)
                            && RelatesController.subAmount(idRelatesSeller, quantity)
                            && AssetNegotiationController.attPriceAsset(attempt.getAsset(), satisfy.getValue())) {
                        return true;
                    }
                } else {
                    RelatesAccountAsset related = new RelatesAccountAsset();
                    related.setAccount(attempt.getAccount());
                    related.setAsset(attempt.getAsset());
                    related.setQuantity(quantity);

                    related.setStart(LocalDateTime.now());

                    if (RelatesController.create(related)
                            && OrderExecutionController.create(executionBuy)
                            && OrderExecutionController.create(executionSeller)
                            && AccountController.transfer(satisfy.getAccount(), value)
                            && RelatesController.subAmount(idRelatesSeller, quantity)
                            && AssetNegotiationController.attPriceAsset(attempt.getAsset(), satisfy.getValue())) {
                        return true;
                    }
                }

            }
            return false;
        } else if (attempt.getType().equals(TypeOrder.SELL)) {
            Order satisfy = databaseServices.verifyOrderSell(attempt);
            if (satisfy != null) {

                BigDecimal value = null;
                Integer quantity = null;

                OrderExecution executionBuy = new OrderExecution();
                OrderExecution executionSeller = new OrderExecution();

                executionBuy.setOrder(satisfy.getId());
                executionSeller.setOrder(attempt.getId());

                executionBuy.setBuyer(satisfy.getAccount());
                executionSeller.setBuyer(satisfy.getAccount());

                executionBuy.setSeller(attempt.getAccount());
                executionSeller.setSeller(attempt.getAccount());

                executionBuy.setStart(LocalDateTime.now());
                executionSeller.setStart(LocalDateTime.now());

                if (Objects.equals(satisfy.getQuantity(), attempt.getQuantity())) {

                    value = attempt.getTotalValue();
                    quantity = attempt.getQuantity();

                    executionBuy.setQuantity(quantity);
                    executionSeller.setQuantity(quantity);

                    databaseServices.updateState(attempt.getId(), StateOrder.TOTAL);
                    databaseServices.updateState(satisfy.getId(), StateOrder.TOTAL);

                    // gerenciar relacionamentos das contas com os ativos
                    // atualizar o preço do ativo
                } else if (satisfy.getQuantity() < attempt.getQuantity()) {

                    value = satisfy.getTotalValue();
                    quantity = satisfy.getQuantity();

                    executionBuy.setQuantity(quantity);
                    executionSeller.setQuantity(quantity);

                    databaseServices.updateState(attempt.getId(), StateOrder.PARCIAL);
                    databaseServices.updateState(satisfy.getId(), StateOrder.TOTAL);

                } else if (satisfy.getQuantity() > attempt.getQuantity()) {

                    value = attempt.getTotalValue();
                    quantity = attempt.getQuantity();

                    executionBuy.setQuantity(quantity);
                    executionSeller.setQuantity(quantity);

                    databaseServices.updateState(attempt.getId(), StateOrder.TOTAL);
                    databaseServices.updateState(satisfy.getId(), StateOrder.PARCIAL);

                }

                Long idRelatesBuyer = RelatesController.requestId(satisfy.getAccount(), attempt.getAsset());
                Long idRelatesSeller = RelatesController.requestId(attempt.getAccount(), attempt.getAsset());

                if (idRelatesBuyer != null) {
                    if (RelatesController.addAmount(idRelatesBuyer, quantity)
                            && OrderExecutionController.create(executionBuy)
                            && OrderExecutionController.create(executionSeller)
                            && AccountController.transferToMe(satisfy.getAccount(), value)
                            && RelatesController.subAmount(idRelatesSeller, quantity)
                            && AssetNegotiationController.attPriceAsset(attempt.getAsset(), attempt.getValue())) {
                        return true;
                    }
                } else {
                    RelatesAccountAsset related = new RelatesAccountAsset();
                    related.setAccount(satisfy.getAccount());
                    related.setAsset(attempt.getAsset());
                    related.setQuantity(quantity);

                    related.setStart(LocalDateTime.now());

                    if (RelatesController.create(related)
                            && OrderExecutionController.create(executionBuy)
                            && OrderExecutionController.create(executionSeller)
                            && AccountController.transferToMe(satisfy.getAccount(), value)
                            && RelatesController.subAmount(idRelatesSeller, quantity)
                            && AssetNegotiationController.attPriceAsset(attempt.getAsset(), attempt.getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean create(Order attempt) {

        attempt.setId(database.createReturnID(attempt));

        return attempt.getId() != null;
    }

    public static List<Order> read() {
        return database.read();
    }

    public static boolean update(Order attempt) {
        if (attempt == null) {
            return false;
        } else {
            return database.update(attempt);
        }
    }

    public static boolean delete(Long id) {
        return database.delete(id);
    }

}
