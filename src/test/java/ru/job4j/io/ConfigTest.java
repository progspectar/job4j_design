package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndEmptyLine() {
        String path = "data/pair_with_comment_and_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1")).isEqualTo("value1");
        assertThat(config.value("key2")).isEqualTo("value2");
        assertThat(config.value("city")).isEqualTo("Moscow");
    }

    @Test
    void whenEqValueThenThrowException() {
        String path = "data/pair_with_eq_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid format in line: ");

    }

    @Test
    void whenKeyEqThenThrowException() {
        String path = "data/pair_with_key_eq.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid format in line: ");
    }

    @Test
    void whenKeyValueThenThrowException() {
        String path = "data/pair_with_key_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid format in line: ");
    }

    @Test
    void whenEqThenThrowException() {
        String path = "data/pair_with_eq.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid format in line: ");
    }

    @Test
    void whenKeyEqValueEqValue() {
        String path = "data/pair_with_key_eq_value_eq_value.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1")).isEqualTo("value1=1");
    }

    @Test
    void whenKeyEqValueEq() {
        String path = "data/pair_with_key_eq_value_eq.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1")).isEqualTo("value1=");
    }
}