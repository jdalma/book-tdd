package tdd.models;

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

    @Test
    @DisplayName("더하기 테스트")
    void testAdd(){
        Money sum = Money.dollar(5).plus(Money.dollar(7));
        assertThat(sum).isEqualTo(new Money(12 , sum.getCurrency()));
    }
}
