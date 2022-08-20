package tdd;

import java.util.Objects;
import java.util.StringJoiner;

public class Money implements Expression{
    private final int amount;
    private final String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    static Money franc(int amount){
        return new Money(amount , "CHF");
    }

    static Money dollar(int amount){
        return new Money(amount , "USD");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;
        return amount == money.amount && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public Expression plus(Expression addend){
        return new Sum(this , addend);
    }

    @Override
    public Money reduce(Bank bank , String to){
        int rate = bank.rate(currency , to);
        return new Money(amount / rate , to);
    }

    public Expression times(int multiplier){
        return new Money(this.amount * multiplier , currency);
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Money.class.getSimpleName() + "[", "]")
                .add("amount=" + amount)
                .add("currency='" + currency + "'")
                .toString();
    }
}
