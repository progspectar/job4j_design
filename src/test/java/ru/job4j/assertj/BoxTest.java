package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisUNKNOWN() {
        Box box = new Box(-5, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void isThisUNKNOWNNegativeEdge() {
        Box box = new Box(4, -1);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void getNumberOfVertices1() {
        Box box = new Box(0, 1);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(0);
    }

    @Test
    void getNumberOfVertices2() {
        Box box = new Box(4, 1);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(4);
    }

    @Test
    void getNumberOfVertices3() {
        Box box = new Box(8, 1);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(8);
    }

    @Test
    void getNumberOfVertices4() {
        Box box = new Box(8, -1);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1);
    }

    @Test
    void isExist() {
        Box box = new Box(8, 8);
        boolean bool = box.isExist();
        assertThat(bool).isEqualTo(true);
    }

    @Test
    void isNotExist() {
        Box box = new Box(-1, -1);
        boolean bool = box.isExist();
        assertThat(bool).isEqualTo(false);
    }

    @Test
    void getArea02() {
        Box box = new Box(0, 2);
        Double res = box.getArea();
        assertThat(res).isEqualTo(50.24d, withPrecision(0.001d));
    }

    @Test
    void getArea42() {
        Box box = new Box(4, 2);
        Double res = box.getArea();
        assertThat(res).isEqualTo(6.9282d, withPrecision(0.0001d));
    }

}