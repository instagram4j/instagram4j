package com.github.instagram4j.instagram4j.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
    
    private SerializeUtil() {}

    public static void serialize(Object o, File to) throws IOException {
        FileOutputStream file = new FileOutputStream(to);
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(o);
        out.close();
        file.close();
    }

    public static <T> T deserialize(File file, Class<T> clazz) throws IOException, ClassNotFoundException {
        InputStream in = new FileInputStream(file);
        T t = deserialize(in, clazz);

        in.close();

        return t;
    }
    
    public static <T> T deserialize(InputStream is, Class<T> clazz) throws IOException, ClassNotFoundException {
        ObjectInputStream oIn = new ObjectInputStream(is);

        T t = clazz.cast(oIn.readObject());
        
        oIn.close();

        return t;
    }
}
