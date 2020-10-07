package com.inmemory;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class InMemoryCompilerTest {

    private InMemoryCompiler compiler;

    @Before
    public void setUp() throws Exception {
        compiler = new InMemoryCompiler();
    }

    @Test
    public void compileNothing() throws Exception {
        String src = "//nada";
        Map<String, byte[]> entries = compiler.compile(src);
        assertEquals(0, entries.keySet().size());
    }

    @Test
    public void compileSingleClass() throws Exception {
        String src = "class Test {}";
        Map<String, byte[]> entries = compiler.compile(src);
        assertEquals(1, entries.keySet().size());
    }

    @Test
    public void compileMultipleClasses() throws Exception {
        String src = "class Test {} class TestTwo {} class TestThree { int main(){ return 0;} }";
        Map<String, byte[]> entries = compiler.compile(src);
        assertEquals(3, entries.keySet().size());
    }

}