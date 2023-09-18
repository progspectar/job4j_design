package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void generalAssertToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> simpleCollection = simpleConvert.toList("1", "2", "2");
        assertThat(simpleCollection).isNotEmpty()
                /*размер:*/
                .hasSize(3)
                /*содержит элементы:*/
                .contains("1", "2", "2")
                /*содержит это в любом порядке, дубликаты не важны:*/
                .containsOnly("2", "2", "1")
                /*содержит только это и только в указанном порядке:*/
                .containsExactly("1", "2", "2")
                /*содержит только это в любом порядке:*/
                .containsExactlyInAnyOrder("2", "1", "2")
                /*содержит хотя бы один из:*/
                .containsAnyOf("1", "2")
                /*не содержит ни одного из:*/
                .doesNotContain("5", "6")
                /*начинается с последовательности:*/
                .startsWith("1", "2")
                /*заканчивается на последовательность:*/
                .endsWith("2", "2")
                /* содержит последовательность:*/
                .containsSequence("2", "2");
    }

    @Test
    void satisfyAssertToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> simpleCollection = simpleConvert.toList("1", "2", "2");
        assertThat(simpleCollection).isNotNull()
                /*все элементы выполняют условие*/
                .allSatisfy(e -> {
                    assertThat(e).isBetween("1", "2");
                    assertThat(e).isGreaterThan("0");
                })
                /*хотя бы один элемент выполняет условие*/
                .anySatisfy(e -> {
                    assertThat(e).isLessThan("5");
                    assertThat(e).isGreaterThanOrEqualTo("2");
                })
                .allMatch(e -> !e.startsWith("3"))
                .anyMatch(e -> e.equals("1")
                );
    }

    @Test
    void checkNavigationListToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> simpleCollection = simpleConvert.toList("1", "2", "2");
        /*первый элемент*/
        assertThat(simpleCollection).first().isEqualTo("1");
        /*элемент по индексу*/
        assertThat(simpleCollection).element(0).isNotNull()
                .isEqualTo("1");
        /*последний элемент*/
        assertThat(simpleCollection).last().isNotNull()
                .isEqualTo("2");
    }

    @Test
    void checkFilteredListToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> simpleCollection = simpleConvert.toList("1", "2", "2");       /*фильтруем источник по предикату и работаем с результатом фильтрации*/
        assertThat(simpleCollection).filteredOn(e -> e.equals("2")).first().isEqualTo("2");
        /*фильтруем с помощью assertThat() и работаем с результатом фильтрации*/
        assertThat(simpleCollection).filteredOnAssertions(e -> assertThat(e).isLessThan("2"))
                .hasSize(1)
                .first().isEqualTo("1");
    }

    @Test
    void generalAssertToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> simpleCollection = simpleConvert.toSet("1", "2", "2", "3", "3");
        assertThat(simpleCollection).isNotEmpty()
                /*размер:*/
                .hasSize(3)
                /*содержит элементы:*/
                .contains("1", "2", "3")
                /*содержит это в любом порядке, дубликаты не важны:*/
                .containsOnly("1", "2", "3")
                /*содержит только это и только в указанном порядке:*/
                .containsExactly("1", "2", "3")
                /*содержит только это в любом порядке:*/
                .containsExactlyInAnyOrder("1", "2", "3")
                /*содержит хотя бы один из:*/
                .containsAnyOf("1", "2", "3")
                /*не содержит ни одного из:*/
                .doesNotContain("4", "5", "6")
                /*начинается с последовательности:*/
                .startsWith("1", "2", "3")
                /*заканчивается на последовательность:*/
                .endsWith("1", "2", "3")
                /* содержит последовательность:*/
                .containsSequence("1", "2", "3");
    }

    @Test
    void satisfyAssertToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> simpleCollection = simpleConvert.toSet("1", "2", "2", "3", "3");
        assertThat(simpleCollection).isNotNull()
                /*все элементы выполняют условие*/
                .allSatisfy(e -> {
                    assertThat(e).isLessThan("4");
                    assertThat(e).isGreaterThan("0");
                })
                /*хотя бы один элемент выполняет условие*/
                .anySatisfy(e -> {
                    assertThat(e).isLessThan("4");
                    assertThat(e).isEqualTo("3");
                })
                .allMatch(e -> e.matches("\\d"));

    }

    @Test
    void checkNavigationListToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> simpleCollection = simpleConvert.toSet("1", "2", "2", "3", "3");
        /*первый элемент*/
        assertThat(simpleCollection).first().isEqualTo("1");
        /*элемент по индексу*/
        assertThat(simpleCollection).element(0).isNotNull()
                .isEqualTo("1");
        /*последний элемент*/
        assertThat(simpleCollection).last().isNotNull()
                .isEqualTo("3");
    }

    @Test
    void checkFilteredListToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> simpleCollection = simpleConvert.toSet("1", "2", "2", "3", "3");
        /*фильтруем источник по предикату и работаем с результатом фильтрации*/
        assertThat(simpleCollection).filteredOn(e -> e.matches("[2-3]")).first().isEqualTo("2");
        /*фильтруем с помощью assertThat() и работаем с результатом фильтрации*/
        assertThat(simpleCollection).filteredOnAssertions(e -> assertThat(e).matches("3"))
                .hasSize(1)
                .first().isEqualTo("3");
    }

    @Test
    void assertMap() {
        Map<Integer, String> map = Map.of(
                1, "1", 2, "2", 3, "3");
        assertThat(map).hasSize(3)
                /*содержит ключи*/
                .containsKeys(1, 3, 2)
                /*содержит значения*/
                .containsValues("3", "1", "2")
                /*не содержит ключ*/
                .doesNotContainKey(0)
                /*не содержит значение*/
                .doesNotContainValue("0")
                /*содержит пару ключ-значение*/
                .containsEntry(2, "2");
    }

    @Test
    void satisfyAssertToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> simpleCollection = new HashMap<>();
        String[] keys = {"1", "2", "3", "3"};
        int[] values = {1, 2, 4, 3};
        for (int i = 0; i < keys.length; i++) {
            simpleCollection.put(keys[i], values[i]);
        }
        assertThat(simpleCollection).isNotNull()
                /*все элементы выполняют условие*/
                .allSatisfy((k, v) -> {
                    assertThat(v).isLessThan(4);
                    assertThat(v).isGreaterThan(0);
                });
    }
}