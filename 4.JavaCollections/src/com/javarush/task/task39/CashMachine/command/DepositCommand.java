package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

class DepositCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = null;
        try {
            currencyCode = ConsoleHelper.askCurrencyCode();
            String[] denominationAndCount = ConsoleHelper.getValidTwoDigits(currencyCode);
            CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
            int denom = Integer.parseInt(denominationAndCount[0]);
            int count = Integer.parseInt(denominationAndCount[1]);
            currencyManipulator.addAmount(denom, count);
            currencyManipulator.getTotalAmount();
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), denom * count, currencyCode));
        } catch (NumberFormatException e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}
