package com.ts.builders

import com.ts.helpers.RestHelper
import groovyx.net.http.ContentType

/**
 * Created by gmazzaglia on 22/8/16.
 */
class RestBuilder {
    String url
    String uriPath
    ContentType contentType = ContentType.JSON
    Map kibanaErrorTags
    Map headers
    Map query
    int retries = 0
    Map bodyParams = [:]

    def withUrl(String url) {
        this.url = url
        return this
    }

    def withContentType(ContentType contentType) {
        this.contentType = contentType
        return this
    }

    def withkibanaErrorTags(Map kibanaErrorTags) {
        this.kibanaErrorTags = kibanaErrorTags
        return this
    }

    def withHeaders(Map headers) {
        this.headers = headers
        return this
    }

    def withQuery(Map query) {
        this.query = query
        return this
    }

    def withRetries(int retries) {
        this.retries = retries
        return this
    }

    def withUriPath(String uriPath) {
        this.uriPath = uriPath
        return this
    }

    def withBodyParams(Map bodyParams) {
        this.bodyParams = bodyParams
        return this
    }

    def build() {
        return new RestHelper(url, contentType, kibanaErrorTags, headers, query, retries, bodyParams, uriPath)
    }
}
