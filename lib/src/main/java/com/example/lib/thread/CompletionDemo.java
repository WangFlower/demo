package com.example.lib.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class CompletionDemo {

    private final ExecutorService executorService;

    public CompletionDemo(ExecutorService executorService) {
        this.executorService = executorService;
    }


    void renderPage(CharSequence source) {
        List<ImageInfo> info = scanForImage(source);//scanforimage

        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        for (final ImageInfo imageInfo : info) {
            completionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });
        }
        renderText(source);

        for (int i = 0; i < info.size(); i++) {
            try {
                Future<String> future = completionService.take();
                String image = future.get();
                renderImage(image);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }

    private void renderText(CharSequence source) {
        // 绘制文本
    }

    private void renderImage(String image) {
        // 绘制图片
    }

    private List<ImageInfo> scanForImage(CharSequence source) {
        return new ArrayList<>();
    }


    private class ImageInfo {

        public String downloadImage() {
            return "";
        }
    }

}
