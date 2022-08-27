package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tdd.Money;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    private final Bank bank = new Bank();

    @BeforeEach
    void setUp() {
        bank.addRate("CHF" , "USD" , 2);
    }

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
    @DisplayName("reduce 테스트")
    void testReduceMoney(){
        Money result = bank.reduce(Money.dollar(1) , "USD");
        assertThat(Money.dollar(1)).isEqualTo(result);
    }


    @Test
    @DisplayName("reduce sum 테스트")
    void testReduceSum(){
        Expression sum = new Sum(Money.dollar(5) , Money.dollar(4));
        Money result = bank.reduce(sum , "USD");
        assertThat(Money.dollar(9)).isEqualTo(result);
    }

    @Test
    @DisplayName("환율 변경 테스트")
    void testMixedAddition(){
        Money fiveBucks = Money.dollar(5);
        Money tenFrancs = Money.franc(10);
        Money result = bank.reduce(fiveBucks.plus(tenFrancs) , "USD");
        assertThat(result).isEqualTo(Money.dollar(10));
    }

    @Test
    void testSumTimes(){
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);

        Expression sum = new Sum(fiveBucks , tenFrancs).times(2);
        Money result = bank.reduce(sum , "USD");
        assertThat(Money.dollar(20)).isEqualTo(result);
    }

    @Test
    void testPlusSameCurrencyReturnMoney(){
        Expression sum = Money.dollar(1).plus(Money.dollar(1));
        assertThat(sum instanceof Money).isTrue();
    }
}
