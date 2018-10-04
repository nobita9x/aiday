package com.aiday.network;

import android.util.Log;

import com.aiday.BuildConfig;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

class LoggingInterceptor implements Interceptor {
    private static final String TAG = "kk";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (BuildConfig.DEBUG) {
            log(request);
        } else {
            String protocolPrefix = request.isHttps() ? "S" : "";
            Log.i(TAG, String.format("---> HTTP%s %s %s",
                    protocolPrefix, request.method(), request.url().toString()));
        }

        long start = System.nanoTime();
        Response response = chain.proceed(request);
        long elapsedTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

        if (BuildConfig.DEBUG) {
            return log(request.url().toString(), response, elapsedTime);
        } else {
            Log.i(TAG, String.format("<--- HTTP %s %s (%sms)",
                    response.code(), request.url().toString(), elapsedTime));
        }
        return response;
    }

    void log(Request r) throws IOException {
        final Request request = r.newBuilder().build();
        String protocolPrefix = request.isHttps() ? "S" : "";
        Log.d(TAG,
                String.format("---> HTTP%s %s %s", protocolPrefix, request.method(),
                        request.url().toString()));

        Headers headers = request.headers();
        for (String header : headers.names()) {
            Log.d(TAG, String.format("%s: %s", header, headers.get(header)));
        }

        String bodySize = "no";
        RequestBody body = request.body();
        if (body != null) {
            MediaType bodyMime = body.contentType();
            if (bodyMime != null) {
                Log.d(TAG, "Content-Type: " + bodyMime);
            }

            long bodyLength = body.contentLength();
            bodySize = bodyLength + "-byte";
            if (bodyLength != -1) {
                Log.d(TAG, "Content-Length: " + bodyLength);
            }
            if (headers.size() > 0) {
                Log.d(TAG, "\n");
            }
            Buffer sink = new Buffer();
            body.writeTo(sink);
            if (bodyMime != null) {
                Log.d(TAG, sink.readUtf8());
            }
        }
        Log.d(TAG, String.format("---> END HTTP%s (%s body)", protocolPrefix, bodySize));
    }

    private Response log(String url, Response response, long elapsedTime)
            throws IOException {
        Response.Builder builder = response.newBuilder();
        Log.d(TAG, String.format("<--- HTTP %s %s (%sms)", response.code(), url, elapsedTime));
        Headers headers = response.headers();
        for (String header : headers.names()) {
            Log.d(TAG, String.format("%s: %s", header, headers.get(header)));
        }
        ResponseBody body = response.body();
        byte[] source = body.bytes();
        MediaType mediaType = body.contentType();
        builder.body(ResponseBody.create(mediaType, source));
        if (headers.size() > 0) {
            Log.d(TAG, "");
        }
        Charset defaultCharset = Charset.forName("UTF-8");
        Charset charset = mediaType != null ? mediaType.charset() != null ? mediaType.charset()
                : defaultCharset : defaultCharset;
        Log.d(TAG, new String(source, charset));
        Log.d(TAG, String.format("<--- END HTTP (%s-byte body)", body.contentLength()));
        return builder.build();
    }
}
