package com.example.upload;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.concurrent.ExecutorService;

public class Upload {


    private final ExecutorService executor;


    private Upload(@NonNull Builder builder) {
        this.executor = builder.executor;
    }

    public static class Builder {

        @Nullable
        private ExecutorService executor;
        @NonNull
        private String uploadUrl;
        @NonNull
        private String uploadFilePath;


        public Builder() {
        }

        public Builder uploadUrl(String uploadUrl) {
            this.uploadUrl = uploadUrl;
            return this;
        }

        public Builder uploadFilePath(String uploadFilePath) {
            this.uploadFilePath = uploadFilePath;
            return this;
        }

        public Upload build() {
            return new Upload(this);
        }

    }
}
