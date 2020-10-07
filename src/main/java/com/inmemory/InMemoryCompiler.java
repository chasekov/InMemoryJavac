package com.inmemory;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.StringWriter;
import java.util.*;

public class InMemoryCompiler {

    private final static String DEFAULT_IDENTIFIER = "IN_MEMORY_COMPILER";
    private final JavaCompiler jc;

    public InMemoryCompiler() throws Exception {
        jc = ToolProvider.getSystemJavaCompiler();
        if( jc == null) throw new Exception( "Java Compiler is unavailable. In Memory Compiler has failed to initialize");
    }

    public Map<String, byte[]> compile(String sourceString) throws Exception {
        return compile(new JavaSourceFromString(DEFAULT_IDENTIFIER, sourceString));
    }

    public Map<String, byte[]> compile(JavaSourceFromString javaSource) throws Exception {
        final InMemoryFileManager memoryFileManager = new InMemoryFileManager(jc.getStandardFileManager(null, null, null));

        Iterable<? extends JavaFileObject> fileObjects = Collections.singletonList(javaSource);
        List<String> options = new ArrayList<>();
        StringWriter output = new StringWriter();

        boolean success = jc.getTask( output, memoryFileManager, null, options, null, fileObjects).call();

        if(!success) {
            throw new Exception( "Compilation failed :" + output);
        }

        Map<String, byte[]> result = memoryFileManager.getAllClassBytes();
        memoryFileManager.clear();
        return result;
    }

}
