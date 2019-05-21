package com.ssos.bf;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class TestUrl {
    public static void main(String[] args) throws IOException {

        TljucUtil.timeTasks(10, 2, () -> {
            long l = System.currentTimeMillis();
            URL url = null;
            try {
                url = new URL("http://t.uavebit.com:9999/group1/M00/00/01/rBp5zFzCrsiAeTOdABGRWTjYyDw729.png");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try (InputStream inputStream = url.openStream();
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                byte[] bytes = new byte[20480];
                while (-1 != inputStream.read(bytes)) {
                    byteArrayOutputStream.write(bytes);
                }
                System.out.println(Thread.currentThread().getName()+":"+(System.currentTimeMillis() - l));
                //Files.write(Paths.get("/Volumes/MAC01/www/Data/" + Thread.currentThread().getName() + ".jpeg"), byteArrayOutputStream.toByteArray());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
