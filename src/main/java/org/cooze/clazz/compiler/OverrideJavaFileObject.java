package org.cooze.clazz.compiler;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Map;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/7/2
 */
public class OverrideJavaFileObject extends SimpleJavaFileObject {
    final String name;

    private Map<String, byte[]> classBytes;
    public OverrideJavaFileObject(String name) {
        super(URI.create("string:///" + name), JavaFileObject.Kind.CLASS);
        this.name = name;
    }

    public OverrideJavaFileObject setClassBytes(final Map<String, byte[]> classBytes){
        this.classBytes = classBytes;
        return this;
    }


    @Override
    public OutputStream openOutputStream() {
        return new FilterOutputStream(new ByteArrayOutputStream()) {
            @Override
            public void close() throws IOException {
                out.close();
                ByteArrayOutputStream bos = (ByteArrayOutputStream) out;
                classBytes.put(name, bos.toByteArray());
            }
        };
    }
}
