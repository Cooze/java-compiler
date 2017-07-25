package org.cooze.clazz.compiler;

import javax.tools.*;
import java.io.IOException;
import java.net.URI;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/6/26
 */
public class OverrideJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {

    protected OverrideJavaFileManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    final Map<String, byte[]> classBytes = new HashMap<>();


    public Map<String, byte[]> getClassBytes() {
        return new HashMap<>(this.classBytes);
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
        classBytes.clear();
    }

    @Override
    public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String className, JavaFileObject.Kind kind,
                                               FileObject sibling) throws IOException {
        if (kind == JavaFileObject.Kind.CLASS) {
            return new OverrideJavaFileObject(className).setClassBytes(this.classBytes);
        } else {
            return super.getJavaFileForOutput(location, className, kind, sibling);
        }
    }

    JavaFileObject makeStringSource(String name, String code) {
        return new InputJavaFileObject(name, code);
    }

    static class InputJavaFileObject extends SimpleJavaFileObject {

        final String code;

        InputJavaFileObject(String name, String code) {
            super(URI.create("string:///" + name), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharBuffer getCharContent(boolean ignoreEncodingErrors) {
            return CharBuffer.wrap(code);
        }
    }
}

