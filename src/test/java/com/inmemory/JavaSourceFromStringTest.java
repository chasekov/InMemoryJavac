package com.inmemory;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class JavaSourceFromStringTest {

    @Test
    public void getCharContent() {
        JavaSourceFromString src = new JavaSourceFromString("Identifier", "class Test {}");
        Assert.assertEquals("class Test {}", src.getCharContent(true));
        Assert.assertEquals("class Test {}", src.getCharContent(false));
    }

    @Test
    public void getCharContent_Extended() {
        JavaSourceFromString src = new JavaSourceFromString("Identifier", "class Test {int x; int y; public int main(){return 1;}}");
        Assert.assertEquals("class Test {int x; int y; public int main(){return 1;}}", src.getCharContent(true));
        Assert.assertEquals("class Test {int x; int y; public int main(){return 1;}}", src.getCharContent(false));
    }
}