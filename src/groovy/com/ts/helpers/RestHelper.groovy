package com.ts.helpers

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

/**
 * Created by gmazzaglia on 22/8/16.
 */
class RestHelper {
    String url
    String uriPath
    ContentType contentType
    Map kibanaErrorTags
    Map headers
    Map query
    int retries
    Map bodyParams

    RestHelper(String url, ContentType contentType, Map kibanaErrorTags, Map headers, Map query, int retries, Map bodyParams, String uriPath) {
        this.url = url
        this.uriPath = uriPath
        this.contentType = contentType
        this.kibanaErrorTags = kibanaErrorTags
        this.headers = headers
        this.query = query
        this.retries = retries
        this.bodyParams = bodyParams
    }

    def request(Method method) {
        def result = [:]

        try {
            def http = new HTTPBuilder(url);

            if (headers) {
                http.setHeaders(headers)
            }

            http.request(method, contentType) { req ->
                if (method == Method.POST) {
                    body = bodyParams
                }
                if (uriPath) {
                    uri.path = uriPath
                }
                if (query) {
                    uri.query = query
                }

                response.success = { resp, reader ->
                    result = [
                            success: true,
                            status : resp.responseBase.statusline.statusCode,
                            reader : reader
                    ]
                }
                response.failure = { resp, reader ->
                    result = [
                            success: false,
                            status : resp.responseBase.statusline.statusCode,
                            reader : reader
                    ]
                }
            }
        } catch (Exception ex) {
            result = [
                    success: false,
                    reader : [
                            error  : ex.getClass().toString(),
                            message: ex.getMessage()
                    ]
            ]
            Logger.kibana(Logger.ERROR, kibanaErrorTags, "ERROR: ${ex.message}");
            NewRelic.noticeError(ex, [url:url, uriPath:uriPath])
        }

        if (!result.success && retries > 0) {
            retries --
            request(method)
        }

        return result
    }

    def doGet() {
        return request(Method.GET)
    }

    def doPost() {
        return request(Method.POST)
    }

    def doPut() {
        return request(Method.PUT)
    }

    def doDelete() {
        return request(Method.DELETE)
    }
}
