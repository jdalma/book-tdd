package tdd.models;

import org.assertj.core.internal.bytebuddy.NamingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @Test
    @DisplayName("equals메소드 테스트")
    void testEquals(){
        assertThat(Money.dollar(5)).isEqualTo(Money.dollar(5));
        assertThat(Money.franc(5)).isEqualTo(Money.franc(5));

        assertThat(Money.franc(5)).isNotEqualTo(Money.dollar(5));
        assertThat(Money.dollar(5)).isNotEqualTo(Money.dollar(6));
    }

    // 1. 테스트 코드 작성 순서 116p
    // 2. reduce를 수행할 책임은 누구한테 있을까 116p
    // 3. Bank와 Expression에 대해


    @Test
    @DisplayName("더하기 테스트")
    void testAdd(){
        Money plusMoney = Money.franc(5).plus(Money.franc(7));
        assertThat(plusMoney).isEqualTo(new Money(12 , plusMoney.getCurrency()));

        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum , "USD");
        assertThat(Money.dollar(10)).isEqualTo(reduced);
    }
}
